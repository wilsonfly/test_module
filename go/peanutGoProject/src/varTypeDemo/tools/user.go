package tools

type user struct {
	Name  string
	Email string
}

type Admin struct {
	Name string
	email string
}

type Root struct {
	user
	Level string
}

func NewUser() user {
	return user{Name: "huasheng", Email: "huasheng@qq.com"}
}

func NewUser2(n, e string) user {
	return user{Name: n, Email: e}
}
