
##terminal 1
docker-compose -f docker-compose-simple.yaml up

##terminal 2
docker exec -it chaincode bash
CORE_PEER_ADDRESS=peer:7051 CORE_CHAINCODE_ID_NAME=peanut:0 ./chaincodeDemo

##terminal 3
docker exec -it container_cli bash
peer chaincode install -p chaincodedev/chaincode/chaincodeDemo -n peanut -v 0
peer chaincode instantiate -n peanut -v 0 -c '{"Args":["hw","hello"]}' -C myc
peer chaincode invoke -n peanut -c '{"Args":["set","hw","helloooooo"]}' -C myc
peer chaincode invoke -n peanut -c '{"Args":["get","hw"]}' -C myc

peer chaincode invoke -n peanut -c '{"Args":["delete","hw"]}' -C myc
peer chaincode invoke -n peanut -c '{"Args":["get","hw"]}' -C myc
peer chaincode invoke -n peanut -c '{"Args":["set","hw","helloooooo"]}' -C myc
peer chaincode invoke -n peanut -c '{"Args":["get","hw"]}' -C myc
peer chaincode invoke -n peanut -c '{"Args":["set","hw1","helloooooo"]}' -C myc
peer chaincode invoke -n peanut -c '{"Args":["set","hw2","helloooooo"]}' -C myc
peer chaincode invoke -n peanut -c '{"Args":["set","hw3","helloooooo"]}' -C myc
peer chaincode invoke -n peanut -c '{"Args":["set","hw5","helloooooo"]}' -C myc
peer chaincode invoke -n peanut -c '{"Args":["getByRange","hw2","hw5"]}' -C myc
peer chaincode invoke -n peanut -c '{"Args":["getByRange","hw5","hw2"]}' -C myc
peer chaincode invoke -n peanut -c '{"Args":["getHistory","hw"]}' -C myc
peer chaincode invoke -n peanut -c '{"Args":["compositeKey"]}' -C myc
##fail to call getQueryResult
peer chaincode invoke -n peanut -c '{"Args":["getQueryResult"]}' -C myc
