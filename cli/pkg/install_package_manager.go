package pkg

import (
	"errors"
	"fmt"
	"os"
	"os/exec"
	"setup/helpers"
)

func InstallPackageManger() (err error) {
	defer func() {
		er := recover()
		if er != nil {
			fmt.Printf("er: %v\n", er)
			err = errors.New("Error installing base packages")
		}
	}()

	message := "Install a package manager? (yes) "

	shouldInstall := helpers.CheckUserInput(message)
	if !shouldInstall {
		return
	}

	cmd := exec.Command("git", "clone", "https://aur.archlinux.org/yay-bin.git")
	cmd.Stdin = os.Stdin
	cmd.Stdout = os.Stdout
	cmd.Stderr = os.Stderr

	exists := helpers.CheckDirectory(cmd.Dir, "yay-bin")
	var removed bool

	if exists {
		removed = helpers.RemoveDirectory(cmd.Dir, "yay-bin", true)
	}

	if exists && !removed {
		fmt.Println("Didn't install package manager")
		return
	}

	err = cmd.Run()
	if err != nil {
		fmt.Println("Error cloning repository! ", err)
		return
	}

	makecmd := exec.Command("makepkg", "-si")
	makecmd.Dir = makecmd.Dir + "yay-bin"
	makecmd.Stdin = os.Stdin
	makecmd.Stdout = os.Stdout
	makecmd.Stderr = os.Stderr

	err = makecmd.Run()
	if err != nil {
		fmt.Println("Error installing package mananger! ", err)
		return
	}

	exists = helpers.CheckDirectory(cmd.Dir, "yay-bin")
	if exists {
		removed = helpers.RemoveDirectory(cmd.Dir, "yay-bin", false)
	}

	fmt.Println("\nPackage manager installed successfully.")
	return
}
