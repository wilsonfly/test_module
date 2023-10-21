#include <stdio.h>
#include "llog_priv.h"
//g++ test.cpp -DDEBUG -I../include -I/data/sunhuasheng/data/test_module-master/origin_c/struct_20230705 -I/data/sunhuasheng/data/test_module-master/origin_c/struct_20230705/../include -DDEBUG_LEVEL=0
//g++ test.cpp -I../include

struct Test
{
    Test(int){}
    Test(){}
    void fun()
    {
        printf("show from fun()\n");
    }
};

int main()
{
	log();
    printf("after sleep\n");

    Test a(1);
    a.fun();

/*build error
 *test.cpp:25:7: error: request for member ‘fun’ in ‘b’, which is of non-class type ‘Test()’
    Test b();
    b.fun();
 */
    Test b;
    b.fun();


}
