#Esse conteiner vai usar node 20 e com o nodo ele vai:
FROM node:20

#cria uma pasta app
WORKDIR /app

#copia tudo do angular pra dentro da app
COPY . .

#instala as dependencias
RUN npm install

#instala a command line do angular
RUN npm install @angular/cli -g

#expoe a porta 4200
EXPOSE 4200

#sobe a aplicação como host no endereço ip padrao da maquina. 
#(fazemos isso porq caso contrario, ele sobe no ambiente proprio e nao dá pra mapear a porta dps)
CMD ["ng", "serve", "--host", "0.0.0.0"]