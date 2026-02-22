📊 AssetGuard Engine
AssetGuard é uma API RESTful de alta performance desenvolvida com Spring Boot 3 para a gestão centralizada de ativos multimercado e monitoramento de custódia financeira.

🚀 Setup & Execução
A infraestrutura é totalmente orquestrada via Docker, garantindo paridade entre ambientes.

Provisionamento: Navegue até a pasta docker/ e execute:

Bash
docker compose -f docker-compose-dev.yml up --build
Bootstrap: O sistema realiza o auto-seed de uma conta administrativa no primeiro boot.

Verificação: O sistema está pronto quando o log exibir: Conta administrativa provisionada com sucesso: master@invest.com.

📎 Arquitetura de Endpoints
1. Governança de Contas (Subscribers)
POST /api/accounts/register - Registro de novo assinante.

GET /api/accounts/directory - Listagem global de perfis (Admin Only).

2. Gestão de Ativos (Holdings)
POST /api/assets/execute - Registro de nova aquisição de ativo.

GET /api/assets/health - Snapshot da saúde financeira da carteira.

3. Consolidação de Custódia (Wallets)
POST /api/wallets/open - Inicialização e vínculo de carteira ao titular.

PUT /api/wallets/sync/{uid} - Sincronização de dados agregados.

🔐 Segurança
O acesso aos recursos é protegido por HTTP Basic Auth. As credenciais mestre provisionadas são:

User: master@invest.com

Pass: invest_2026_secure
