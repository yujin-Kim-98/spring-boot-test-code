insert into `users` (`id`, `email`, `nickname`, `address`, `certification_code`, `status`, `last_login_at`)
values (1, 'yujin123.kim@gmail.com', 'yujji', 'Seoul', 'aaaaaaa-aa-aaaaaa', 'ACTIVE', 0);

insert into `users` (`id`, `email`, `nickname`, `address`, `certification_code`, `status`, `last_login_at`)
values (2, 'yujin1234.kim@gmail.com', 'yujji', 'Seoul', 'aaaaaaa-aa-aaaaaab', 'PENDING', 0);

insert into `posts` (`id`, `content`, `created_at`, `modified_at`, `user_id`)
values (1, 'hello', 1678530673958, 0, 1);