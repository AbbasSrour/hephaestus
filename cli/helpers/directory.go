package helpers

import (
	"os"
)

func CheckDirectory(path string, name string) (exists bool) {
	var err error
	var directory string = path + name
	_, err = os.Stat(directory)
	if err != nil {
		return false
	}

	return true
}

func RemoveDirectory(path string, name string, check bool) (removed bool) {
	var err error
	var directory string = path + name
	var shouldRemove bool

	message := "Directory " + name + "already exists should I remove it? (yes)"

	shouldRemove = true
	if check {
		shouldRemove = CheckUserInput(message)
	}

	if !shouldRemove {
		return
	}

	err = os.RemoveAll(directory)
	if err != nil {
		return false
	}

	removed = true

	return
}
