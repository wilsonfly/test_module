package util
import "C"

/*
//#include "sum.c"  //只include 头文件即可，如果是include sum.c会有如下报错：
duplicate symbol _sum in:
    $WORK/b002/_x002.o
    $WORK/b002/_x003.o
ld: 1 duplicate symbol for architecture x86_64
clang: error: linker command failed with exit code 1 (use -v to see invocation)
*/

/*
//#cgo CFLAGS: -I../include

#include "sum.h"
 */
import "C"
import "fmt"

func GoSum() {
	a := C.sum(2, 3)
	fmt.Println(a)

	b := C.sum(C.int(2), C.int(3))
	fmt.Println(b)

	c := C.stringSum(C.int(2), C.char('a'), C.CString("hello"))
	fmt.Println(c)

	d := C.GoString( C.stringSum(C.int(2), C.char('a'), C.CString("hello")) )
	fmt.Println(d)

	//cannot use "hello" (type string) as type *_Ctype_char in argument to _Cfunc_stringSum
	//e := C.GoString( C.stringSum(2, 'a', "hello") )
	//fmt.Println(e)

	f := C.GoString( C.stringSum(2, 'a', C.CString("hello")) )
	fmt.Println(f)
}
