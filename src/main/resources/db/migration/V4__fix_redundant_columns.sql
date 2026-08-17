-- V4: Permitir NULL en usuario_id redundante para familiares y cuidadores
ALTER TABLE `familiares` MODIFY `usuario_id` bigint NULL;
ALTER TABLE `cuidadores` MODIFY `usuario_id` bigint NULL;