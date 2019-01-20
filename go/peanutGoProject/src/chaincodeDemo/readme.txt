
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
