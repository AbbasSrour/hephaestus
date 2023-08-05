package pkg

import (
	"errors"
	"fmt"
	"os"
	"os/exec"
	"setup/helpers"
)

func RefreshMirrors() (err error) {
	defer func() {
		er := recover()
		if er != nil {
			fmt.Printf("er: %v\n", er)
			err = errors.New("Error installing base packages")
		}
	}()
	message := "Refresh the mirrors? (yes) "
	shouldRefresh := helpers.CheckUserInput(message)
	if !shouldRefresh {
		return
	}

	reflectorInstalled := helpers.IsPkgInstalled("reflector")
	if !reflectorInstalled {
		if err = helpers.InstallPkg("reflector"); err == nil {
			reflectorInstalled = true
		}
	}

	refresh := exec.Command("reflector")
	refresh.Stdin = os.Stdin
	refresh.Stdout = os.Stdout
	refresh.Stderr = os.Stderr
	err = refresh.Run()
	if err != nil {
		fmt.Println("\nError installing reflector:", err)
		return
	}

	return
}
