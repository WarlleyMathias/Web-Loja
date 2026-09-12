# 🛒 WebLoja - Sistema de E-Commerce com Spring Boot & Thymeleaf

![Java](https://img.shields.io/badge/Java-11%2B-orange?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.4.5-brightgreen?style=flat-square&logo=springboot)
![Spring Security](https://img.shields.io/badge/Spring%20Security-OAuth2-green?style=flat-square&logo=springsecurity)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.x-005F0F?style=flat-square&logo=thymeleaf)

> **Nota sobre o repositório:** Este projeto foi originalmente desenvolvido em 2022 como parte dos meus estudos de consolidação do ecossistema Spring. O repositório foi mantido em seu estado funcional original para registrar a evolução da minha jornada como desenvolvedor backend.

---

## 📌 Sobre o Projeto

O **WebLoja** é uma aplicação Full Stack / Monolítica focada em gerenciamento de produtos, pedidos e usuários para uma plataforma de e-commerce. O sistema conta com controle de acesso baseado em funções (RBAC), autenticação robusta via Spring Security/OAuth2 e manipulação de arquivos estáticos.

### 🛠️ Tecnologias Utilizadas

- **Backend:** Java, Spring Boot, Spring Data JPA, Spring Security (OAuth2 Client)
- **Frontend:** HTML5, CSS3, Thymeleaf, Spring Security HTML Extra Tags
- **Banco de Dados:** MySQL / H2 Database
- **Gerenciador de Dependências:** Apache Maven

---

## ⚙️ Principais Funcionalidades

- 🔐 **Autenticação e Autorização:**
  - Login tradicional com controle de permissões por perfis (`ROLE_USER`, `ROLE_ADMIN`).
  - Suporte a login social via **OAuth2**.
- 📦 **Gestão de Produtos e Categorias:**
  - Operações completas de CRUD para catálogo de produtos.
  - Upload e vínculo de imagens aos produtos.
- 👥 **Gestão de Usuários:**
  - Mapeamento de relacionamentos N:N (`@ManyToMany`) entre Usuários e Papéis/Roles.
- 🎨 **Interface Server-Side:**
  - Renderização dinâmica de telas via Thymeleaf integradas às regras do Spring Security.

---

## 🚀 Como Executar o Projeto Localmente

### Pré-requisitos
- JDK 11 ou superior instalado.
- Maven (ou utilize o `./mvnw` incluso na raiz do projeto).
- Banco de dados MySQL rodando (ou ajuste para H2 no `application.properties`).

### Passo a passo

1. **Clone o repositório:**
   ```bash
   git clone [https://github.com/SEU_USUARIO/Web-Loja.git](https://github.com/SEU_USUARIO/Web-Loja.git)
   cd Web-Loja
