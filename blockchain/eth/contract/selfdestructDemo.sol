pragma solidity ^0.4.0;
contract selfdestructDemo{
    uint internal u = 10;
    
    event e(address addr);
    function test() returns(uint){
        return 100;
    }
    
    function selfdestructDemo() payable{
        
    }
    function kill(address add){
        e(add);
        selfdestruct(add);
    }
}