DELETE FROM oauth_client_details WHERE client_id = 'homenvi';
INSERT INTO oauth_client_details(client_id, resource_ids, client_secret, scope, authorized_grant_types,
                                 web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity,
                                 additional_information, autoapprove)
VALUES ('homenvi', null, 'homenvi123', 'homenvi', 'password', '/index', 'all', null, null, null, null);