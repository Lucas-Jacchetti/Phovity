# Phovity

Um mvp de rede social para fotógrafos onde usuários podem publicar, explorar e salvar imagens.
O site está disponível em https://phovity.vercel.app/login

## Tecnologias utilizadas

### Backend

* Java + Spring Boot
* Spring Security + JWT
* JPA / Hibernate
* PostgreSQL (Aiven)
* Deploy no Render
* Imagens guardadas no Cloudnary
  
### Frontend

* Next.js
* React
* TailwindCSS
* Deploy na vercel

## Funcionalidades

* Cadastro e login de usuários
* Criação de posts com imagem
* Feed de posts
* Visualização de post individual
* Posts do próprio usuário
* Sistema de salvar/curtir posts

## Autenticação

* Autenticação baseada em JWT
* Token armazenado em cookie HttpOnly
* Filtro customizado para validação de token
* Rotas protegidas com Spring Security

## Upload de imagens

As imagens são enviadas diretamente para o Cloudinary no frontend.
