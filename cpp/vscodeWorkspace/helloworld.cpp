#include <iostream>
#include <vector>
using namespace std;

int main()
{
    for (int i = 0; i < 5; i++)
    {
        cout << "hello world for c++" << endl;
    }

    vector<int> v;
    v.push_back(10);
    v.push_back(20);
    v.push_back(40);
    v.push_back(30);
    v.push_back(30);
    for (vector<int>::iterator i = v.begin(); i != v.end(); i++)
    {
        cout << *i << " ";
    }
    cout << endl;
    return 0;
}