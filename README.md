sudo docker run --name cont_postgresql -itd -p 5432:5432 \
--restart always -e DB_NAME=sampledb -e DB_USER=sample \
-e DB_PASS=12345 mental/postgresql:9.6-2
