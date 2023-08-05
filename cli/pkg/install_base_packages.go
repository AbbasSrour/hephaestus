package pkg

import (
	"errors"
	"fmt"
	"os"
	"os/exec"
	"setup/helpers"
)

func InstallBasePackages() (err error) {
	defer func() {
		er := recover()
		if er != nil {
			fmt.Printf("er: %v\n", er)
			fmt.Println("Error installing base packages")
			err = errors.New("error.base_packages.unknown")
		}
	}()

	message := "Install base packages? (yes)"
	shouldInstall := helpers.CheckUserInput(message)
	if !shouldInstall {
		return
	}

	cmd := exec.Command("bash", "-c", "sudo pacman -S git base-devel")
	cmd.Stdout = os.Stdout
	cmd.Stderr = os.Stderr
	cmd.Stdin = os.Stdin

	err = cmd.Run()
	if err != nil {
		fmt.Println("\nError starting package manager installation:", err)
		helpers.RemoveDBLock()
		return
	}

	return
}
