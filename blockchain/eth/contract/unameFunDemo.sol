pragma solidity ^0.4.0;

contract C1 {
    uint public data;
    
    event e(address from,uint _u);
    function() payable{
        data += 1;
        e(msg.sender,data);
    }
}



contract CallC1{
    event e(bool _b);
    function CallC1() payable{
        
    }
    function callTest(address add) returns(bool){
        bool b = add.call(bytes4(keccak256("setMoney")));
        e(b);
        return b;
    }
}

