#include <stdio.h>
#include <stdlib.h>
#define HASHSIZE 11

/* 哈希表元素的结构定义*/
typedef struct
{
	int ID;
	char *name;
	float salary;
} employee;

typedef employee DataType;

/* 定义一个全局的，元素类型为employee 的哈希表*/
DataType Hash[HASHSIZE];

/*
函数名：Create_Hash
参数：employees为员工资料数组，size为数组大小
功能：将大小为size的员工资料数组按员工ID映射到Hash表
*/
void Create_Hash(DataType *employees, int size);

/*函数名：HashFun
参数：key为员工ID
功能：将员工ID映射为Hash表中的下标地址
返回值：返回给定关键字对应的Hash表下标地址
*/
int HashFun(int key);

/*函数名：HashSearch
参数：key为员工的ID
功能：在Hash表中搜索给定关键字的员工信息
返回值：找到返回，并输出员工资料，找不到返回并提示没有找到
*/
int HashSearch(int key);

/*函数名：OverHandle
参数：address发生冲突Hash表下标地址
功能：
返回值：
*/
int OverHandle(int address);

/*函数名：printemployee
参数：一个员工资料结构的指针
功能：屏幕输出员工资料
返回值：无
*/
void printemployee(DataType *employee)
{
	printf("ID : %d \t Name: %s\t Salary:%f\n",
		employee->ID, employee->name, employee->salary);
}

int main(int argc, char* argv[])
{
	int size;
	int key1;
	static char ch;
	/* 员工资料数组*/
	DataType employee[] = {
		{11, "luojiafeng", 5000},
		{37, "wangqian", 8000},
		{48, "liujie", 6000},
		{97, "gaoxing", 10000},
		{86, "xiaozhen", 6000},
		{26, "chenghu", 8800}
	};
	/* 数组元素个数*/
	size = sizeof(employee) / sizeof(employee[0]);

	/* 将员工资料数组映射到哈希表*/
	Create_Hash(employee, size);

	/* 输入一个员工的ID，查找并显示相关信息*/
	printf("请输入一位员工的ID：\n");
	scanf("%d", &key1);
	HashSearch(key1);
}

void Create_Hash(DataType *employees, int size)
{
	int i, j;
	DataType empty = {0, NULL, 0.0};
	for (i = 0; i < HASHSIZE; i++)
	{
		Hash[i] = empty;
	}
	for (i = 0; i < size; i++)
	{
		j = 0;
		while (j < HASHSIZE)
		{
			/* 根据员工ID，将员工资料存放到哈表*/
			if (Hash[(employees[i].ID % HASHSIZE) + j].ID == 0)
			{
				Hash[(employees[i].ID % HASHSIZE) + j] = employees[i];
				break;
			}
			/* j++表示发生了冲突*/
			else
				j++;
		}
	}
}

int HashFun(int key)
{
	return key % HASHSIZE;
}

DataType HashValue(int key)
{
	return Hash[key % HASHSIZE];
}

int HashSearch(int key)
{
	DataType temp;
	int address, count = 0;
	address = HashFun(key);
	count++;
	temp = HashValue(address);
	if (temp.ID == key)
	{
		printemployee(&temp);
		return 1;
	}
	else if (temp.ID == 0)
	{
		printf("没有找到与您输入ID相关的记录!\n");
		return 0;
	}
	else
	{
		while (count < HASHSIZE)
		{
			address = OverHandle(address);
			temp = HashValue(address);
			if (temp.ID == key)
			{
				printemployee(&temp);
				return 1;
			}
			count++;
		}
	}
	return 0;
}

int OverHandle(int address)
{
	return (address+1) % HASHSIZE;
}