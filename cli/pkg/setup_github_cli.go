package pkg

import (
	"errors"
	"fmt"
	"os"
	"os/exec"
	"setup/helpers"
	"strings"
)

func SetupGithubCli() (err error) {
	defer func() {
		er := recover()
		if er != nil {
			fmt.Printf("er: %v\n", er)
			err = errors.New("error.github_cli_setup.unexpected")
		}
	}()

	message := "Setup Github?(yes) "
	setupGh := helpers.CheckUserInput(message)
	if !setupGh {
		return
	}

	if ghInstalled := helpers.IsPkgInstalled("github-cli"); !ghInstalled {
		if err := helpers.InstallPkg("github-cli"); err != nil {
			fmt.Println("Error installing github cli!")
			return err
		}
	}

	profileExtensionInstalled := InstallGhProfileExtension()

	if profileExtensionInstalled {
		firstProfile := true
		AddGithubProfile(&firstProfile)
	}

	return
}

func InstallGhProfileExtension() (extensionInstalled bool) {
	getExtensions := exec.Command("gh", "extension", "list")

	if output, err := getExtensions.Output(); err == nil {
		extensions := strings.Split(string(output), "\n")
		for _, extension := range extensions {
			metadata := strings.Fields(extension)
			if metadata[2] == "gabe565/gh-profile" {
				extensionInstalled = true
				return
			}
		}
	}

	if !extensionInstalled {
		var message = "Add gh profile extension? (yes)"
		if installExtension := helpers.CheckUserInput(message); !installExtension {
			return
		}

		cmd := exec.Command("gh", "extension", "install", "gabe565/gh-profile")
		cmd.Stdin = os.Stdin
		cmd.Stdout = os.Stdout
		cmd.Stderr = os.Stderr

		if err := cmd.Run(); err != nil {
			fmt.Println("Error installing extension")
		}

		extensionInstalled = true
	}

	return
}

func AddGithubProfile(firstProfile *bool) {
	for {
		var message string
		if *firstProfile {
			message = "Add a profile to github cli?"
		} else {
			message = "Add another profile to github cli?"
		}

		if input := helpers.CheckUserInput(message); input == false {
			break
		}

		cmd := exec.Command("gh", "profile", "create")
		cmd.Stdin = os.Stdin
		cmd.Stdout = os.Stdout
		cmd.Stderr = os.Stderr

		if err := cmd.Run(); err != nil {
			fmt.Println("Error occurred while adding your profile!")
			message = "Try again?"
			if input := helpers.CheckUserInput(message); input == false {
				break
			}

			continue
		}

		message = "Do you want to authenticate your new profile with github?"
		if login := helpers.CheckUserInput(message); login {
			GithubCliLogin()
		}

		*firstProfile = false
	}
}

func GithubCliLogin() {
	loginCmd := exec.Command("gh", "auth", "login")
	loginCmd.Stdin = os.Stdin
	loginCmd.Stdout = os.Stdout
	loginCmd.Stderr = os.Stderr

	if err := loginCmd.Run(); err != nil {
		if tryAgain := helpers.CheckUserInput("An error has occurred try again?"); tryAgain {
			GithubCliLogin()
		}
	}
}

func InstallSqlite() {
	sqliteInstalled := helpers.IsPkgInstalled("sqlite")
	if !sqliteInstalled {
		if err := helpers.InstallPkg("sqlite"); err == nil {
			sqliteInstalled = true
		}
	}
}
