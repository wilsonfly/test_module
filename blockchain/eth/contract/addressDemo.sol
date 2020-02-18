pragma solidity ^0.4.0;
contract addressDemo{
    function addressDemo() payable {
        
    }
    
    function sendDemo(address add){
        uint u = 1 ether;
        add.transfer(u);
    }
}