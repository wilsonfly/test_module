pragma solidity ^0.4.0;

contract modifierDemo {
    address public owner;
    uint public u;
    
    function modifierDemo(){
        owner = msg.sender;
    }
    
    modifier onlyOwner{
        if(msg.sender !=owner ){
            throw;
        }else{
            _;
        }
    }
    
    function set(uint _u) onlyOwner{
        u = _u;
    }
}