Criado um Dynamic Web Project, juntamente com o gerenciador de dependências Maven. Utilizado jre 1.8;
É necessário importar o projeto no Eclipse, atualizar as dependências através do Maven e ter o jre 1.8.
Para rodar a aplicação foi utilizado o server Tomcat v7.0. 
Após rodar a aplicação no servidor, a mesma pode ser acessada pela url http://localhost:8080/Forecast/, ou através da porta configurada.
No momento que for acessada a página, será criado um diretório/arquivo em: C:\temp\Cidades.json
Na página web, foi adicionada ao lado esquerdo uma opção para adicionar cidades, juntamente de onde é exibida a listagem. 
Ao selecionar uma cidade listada, no painel direito será exibida a previsão dos próximos 5 dias, assim como a previsão por hora.
No back-end foi configurado um webservice rest para as ações de busca dos dados e gravação no arquivo.
Para a busca da previsão do tempo foi utilizada a lib OWM, disponibilizada no próprio site da API.
Para os teste, adicionar junit ao build path.