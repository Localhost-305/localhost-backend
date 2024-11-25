#!/bin/bash

# Com as configurações atuais, o client faz uma requisição por segundo,
# Para aumentar o número de requisições por segundo, descomente as linhas 
# 14 e 32 e comente a linha 33, você terá que remover tanto a imagem como
# o container client-forum-api e subir a stack novamente para o rebuild.

HOST='host.docker.internal:9090'

while true
    do
	ENDP=`expr $RANDOM % 24 + 1`
	NUMB=`expr $RANDOM % 100 + 1`
	# TEMP=`expr 1 + $(awk -v seed="$RANDOM" 'BEGIN { srand(seed); printf("%.4f\n", rand()) }')`
        
	if [ $NUMB -le 55 ]; then
	    curl --write-out "%{http_code}\n" --silent --output /dev/null http://${HOST}/amount/collected?months=6
        elif [ $NUMB -ge 56 ] && [ $NUMB -le 85 ] ; then
	    curl --write-out "%{http_code}\n" --silent --output /dev/null http://${HOST}/amount/collected?months=$ENDP
        elif [ $NUMB -ge 86 ] && [ $NUMB -le 95 ] ; then
	    curl --write-out "%{http_code}\n" --silent --output /dev/null --data '{"email":"api@email.com","password":"1234"}' \
		 --header "Content-Type:application/json" \
		 --request POST http://${HOST}/auth/login
        elif [ $NUMB -ge 96 ] && [ $NUMB -le 98 ] ; then
	    curl --write-out "%{http_code}\n" --silent --output /dev/null --data '{"email":"moderador@email.com","password":"12345"}' \
	         --header "Content-Type:application/json" \
	         --request POST http://${HOST}/auth/login
	else
	    curl --write-out "%{http_code}\n" --silent --output /dev/null http://${HOST}/amount/collected?months=6
        fi

	# sleep $TEMP
	sleep 0.75
done

