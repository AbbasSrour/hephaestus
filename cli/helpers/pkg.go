package helpers

import (
	"fmt"
	"os"
	"os/exec"
)

func IsPkgInstalled(pkg string) bool {
	var err error

	checkcmd := exec.Command("bash", "-c", "sudo pacman -Qi "+pkg)
	checkcmd.Stderr = os.Stderr
	checkcmd.Stdin = os.Stdin

	if err = checkcmd.Run(); err == nil {
		return true
	}

	return false
}

func InstallPkg(pkg string) (err error) {
	cmd := exec.Command("bash", "-c", "sudo pacman -S"+pkg)
	cmd.Stdin = os.Stdin
	cmd.Stdout = os.Stdout
	cmd.Stderr = os.Stderr

	err = cmd.Run()
	if err != nil {
		fmt.Println("\nError installing "+pkg+": ", err)
		RemoveDBLock()
		return
	}

	return nil
}

func RemoveDBLock() {
	lockFilePath := "/var/lib/pacman/db.lck"

	_, err := os.Stat(lockFilePath)
	if os.IsNotExist(err) {
		fmt.Println("DB lock file does not exist.")
		return
	}

	cmd := exec.Command("sudo", "rm", lockFilePath)
	err = cmd.Run()
	if err != nil {
		fmt.Println("Error removing DB lock:", err)
	}
}
