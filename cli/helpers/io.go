package helpers

import (
	"bufio"
	"fmt"
	"os"
	"strings"
)

func CheckUserInput(message string) (check bool) {
	var input string

	fmt.Print(message)
	GetUserInput(&input)

	switch input {
	case "yes":
		check = true
	case "no":
		check = false
	case "":
		check = true
	default:
		fmt.Println("Bad input")
		check = CheckUserInput(message)
	}

	return check
}

// func GetUserInput(input *string) {
// 	ctx, cancel := context.WithTimeout(context.Background(), 10*time.Second)
// 	defer cancel()

// 	var channel = make(chan string, 1)
// 	in := bufio.NewReader(os.Stdin)

// 	go func() {
// 		defer close(channel)

// 		result, err := in.ReadString('\n')
// 		if err != nil {
// 			log.Println("Error geting user input !")
// 			channel <- "\n"
// 			return
// 		}

// 		channel <- result
// 		return
// 	}()

// 	select {
// 	case i := <-channel:
// 		*input = strings.TrimSuffix(i, "\n")
// 	case <-ctx.Done():
// 		*input = ""
// 	}
// }

func GetUserInput(holder *string) {
	reader := bufio.NewReader(os.Stdin)
	input, err := reader.ReadString('\n')
	if err != nil {
		fmt.Println("An error has occured, exiting application...")
		os.Exit(1)
	}
	input = strings.TrimSuffix(input, "\n")
	*holder = input
}
