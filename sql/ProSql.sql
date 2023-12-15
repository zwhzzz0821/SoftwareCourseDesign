DROP TABLE IF EXISTS "code_column_config";
CREATE TABLE "code_column_config" (
  "column_id" int8 NOT NULL,
  "table_name" varchar(180),
  "column_name" varchar(255),
  "column_type" varchar(255),
  "dict_name" varchar(255),
  "extra" varchar(255),
  "form_show" varchar(1),
  "form_type" varchar(255),
  "key_type" varchar(255),
  "list_show" varchar(1),
  "not_null" varchar(1),
  "query_type" varchar(255),
  "remark" varchar(255),
  "date_annotation" varchar(255),
  PRIMARY KEY ("column_id")
);
CREATE INDEX "idx_table_name" ON "code_column_config" USING btree (
  "table_name" ASC
);
COMMENT ON COLUMN "code_column_config"."column_id" IS 'ID';
COMMENT ON TABLE "code_column_config" IS '代码生成字段信息存储';

CREATE TABLE "code_gen_config" (
  "config_id" int8 NOT NULL,
  "table_name" varchar(255),
  "author" varchar(255),
  "cover" varchar(1),
  "module_name" varchar(255),
  "pack" varchar(255),
  "path" varchar(255),
  "api_path" varchar(255),
  "prefix" varchar(255),
  "api_alias" varchar(255),
  CONSTRAINT "_copy_26" PRIMARY KEY ("config_id")
);
CREATE INDEX "idx_table_name_copy_1" ON "code_gen_config" USING btree (
  "table_name" ASC
);
COMMENT ON COLUMN "code_gen_config"."config_id" IS 'ID';
COMMENT ON COLUMN "code_gen_config"."table_name" IS '表名';
COMMENT ON COLUMN "code_gen_config"."author" IS '作者';
COMMENT ON COLUMN "code_gen_config"."cover" IS '是否覆盖';
COMMENT ON COLUMN "code_gen_config"."module_name" IS '模块名称';
COMMENT ON COLUMN "code_gen_config"."pack" IS '至于哪个包下';
COMMENT ON COLUMN "code_gen_config"."path" IS '前端代码生成的路径';
COMMENT ON COLUMN "code_gen_config"."api_path" IS '前端Api文件路径';
COMMENT ON COLUMN "code_gen_config"."prefix" IS '表前缀';
COMMENT ON COLUMN "code_gen_config"."api_alias" IS '接口名称';
COMMENT ON TABLE "code_gen_config" IS '代码生成器配置';

CREATE TABLE "mnt_app" (
  "app_id" int8 NOT NULL,
  "name" varchar(255),
  "upload_path" varchar(255),
  "deploy_path" varchar(255),
  "backup_path" varchar(255),
  "port" int4,
  "start_script" varchar(4000),
  "deploy_script" varchar(4000),
  "create_by" varchar(255),
  "update_by" varchar(255),
  "create_time" timestamp,
  "update_time" timestamp,
  CONSTRAINT "_copy_25" PRIMARY KEY ("app_id")
);
COMMENT ON COLUMN "mnt_app"."app_id" IS 'ID';
COMMENT ON COLUMN "mnt_app"."name" IS '应用名称';
COMMENT ON COLUMN "mnt_app"."upload_path" IS '上传目录';
COMMENT ON COLUMN "mnt_app"."deploy_path" IS '部署路径';
COMMENT ON COLUMN "mnt_app"."backup_path" IS '备份路径';
COMMENT ON COLUMN "mnt_app"."port" IS '应用端口';
COMMENT ON COLUMN "mnt_app"."start_script" IS '启动脚本';
COMMENT ON COLUMN "mnt_app"."deploy_script" IS '部署脚本';
COMMENT ON COLUMN "mnt_app"."create_by" IS '创建者';
COMMENT ON COLUMN "mnt_app"."update_by" IS '更新者';
COMMENT ON COLUMN "mnt_app"."create_time" IS '创建日期';
COMMENT ON COLUMN "mnt_app"."update_time" IS '更新时间';
COMMENT ON TABLE "mnt_app" IS '应用管理';

CREATE TABLE "mnt_database" (
  "db_id" varchar(50) NOT NULL,
  "name" varchar(255) NOT NULL,
  "jdbc_url" varchar(255) NOT NULL,
  "user_name" varchar(255) NOT NULL,
  "pwd" varchar(255) NOT NULL,
  "create_by" varchar(255),
  "update_by" varchar(255),
  "create_time" timestamp,
  "update_time" timestamp,
  CONSTRAINT "_copy_24" PRIMARY KEY ("db_id")
);
COMMENT ON COLUMN "mnt_database"."db_id" IS 'ID';
COMMENT ON COLUMN "mnt_database"."name" IS '名称';
COMMENT ON COLUMN "mnt_database"."jdbc_url" IS 'jdbc连接';
COMMENT ON COLUMN "mnt_database"."user_name" IS '账号';
COMMENT ON COLUMN "mnt_database"."pwd" IS '密码';
COMMENT ON COLUMN "mnt_database"."create_by" IS '创建者';
COMMENT ON COLUMN "mnt_database"."update_by" IS '更新者';
COMMENT ON COLUMN "mnt_database"."create_time" IS '创建时间';
COMMENT ON COLUMN "mnt_database"."update_time" IS '更新时间';
COMMENT ON TABLE "mnt_database" IS '数据库管理';

CREATE TABLE "mnt_deploy" (
  "deploy_id" int8 NOT NULL,
  "app_id" int8,
  "create_by" varchar(255),
  "update_by" varchar(255),
  "create_time" timestamp,
  "update_time" timestamp,
  CONSTRAINT "_copy_23" PRIMARY KEY ("deploy_id")
);
CREATE INDEX "FK6sy157pseoxx4fmcqr1vnvvhy" ON "mnt_deploy" USING btree (
  "app_id" ASC
);
COMMENT ON COLUMN "mnt_deploy"."deploy_id" IS 'ID';
COMMENT ON COLUMN "mnt_deploy"."app_id" IS '应用编号';
COMMENT ON COLUMN "mnt_deploy"."create_by" IS '创建者';
COMMENT ON COLUMN "mnt_deploy"."update_by" IS '更新者';
COMMENT ON COLUMN "mnt_deploy"."update_time" IS '更新时间';
COMMENT ON TABLE "mnt_deploy" IS '部署管理';

CREATE TABLE "mnt_deploy_history" (
  "history_id" varchar(50) NOT NULL,
  "app_name" varchar(255) NOT NULL,
  "deploy_date" timestamp NOT NULL,
  "deploy_user" varchar(50) NOT NULL,
  "ip" varchar(20) NOT NULL,
  "deploy_id" int8,
  CONSTRAINT "_copy_22" PRIMARY KEY ("history_id")
);
COMMENT ON COLUMN "mnt_deploy_history"."history_id" IS 'ID';
COMMENT ON COLUMN "mnt_deploy_history"."app_name" IS '应用名称';
COMMENT ON COLUMN "mnt_deploy_history"."deploy_date" IS '部署日期';
COMMENT ON COLUMN "mnt_deploy_history"."deploy_user" IS '部署用户';
COMMENT ON COLUMN "mnt_deploy_history"."ip" IS '服务器IP';
COMMENT ON COLUMN "mnt_deploy_history"."deploy_id" IS '部署编号';
COMMENT ON TABLE "mnt_deploy_history" IS '部署历史管理';

CREATE TABLE "mnt_deploy_server" (
  "deploy_id" int8 NOT NULL,
  "server_id" int8 NOT NULL,
  CONSTRAINT "_copy_21" PRIMARY KEY ("deploy_id", "server_id")
);
CREATE INDEX "FKeaaha7jew9a02b3bk9ghols53" ON "mnt_deploy_server" USING btree (
  "server_id" ASC
);
COMMENT ON COLUMN "mnt_deploy_server"."deploy_id" IS '部署ID';
COMMENT ON COLUMN "mnt_deploy_server"."server_id" IS '服务ID';
COMMENT ON TABLE "mnt_deploy_server" IS '应用与服务器关联';

CREATE TABLE "mnt_server" (
  "server_id" int8 NOT NULL,
  "account" varchar(50),
  "ip" varchar(20),
  "name" varchar(100),
  "password" varchar(100),
  "port" int4,
  "create_by" varchar(255),
  "update_by" varchar(255),
  "create_time" timestamp,
  "update_time" timestamp,
  CONSTRAINT "_copy_20" PRIMARY KEY ("server_id")
);
CREATE INDEX "idx_ip" ON "mnt_server" USING btree (
  "ip" ASC
);
COMMENT ON COLUMN "mnt_server"."server_id" IS 'ID';
COMMENT ON COLUMN "mnt_server"."account" IS '账号';
COMMENT ON COLUMN "mnt_server"."ip" IS 'IP地址';
COMMENT ON COLUMN "mnt_server"."name" IS '名称';
COMMENT ON COLUMN "mnt_server"."password" IS '密码';
COMMENT ON COLUMN "mnt_server"."port" IS '端口';
COMMENT ON COLUMN "mnt_server"."create_by" IS '创建者';
COMMENT ON COLUMN "mnt_server"."update_by" IS '更新者';
COMMENT ON COLUMN "mnt_server"."create_time" IS '创建时间';
COMMENT ON COLUMN "mnt_server"."update_time" IS '更新时间';
COMMENT ON TABLE "mnt_server" IS '服务器管理';

CREATE TABLE "sys_dept" (
  "dept_id" int8 NOT NULL,
  "pid" int8,
  "sub_count" int4,
  "name" varchar(255) NOT NULL,
  "dept_sort" int4,
  "enabled" varchar(1) NOT NULL,
  "create_by" varchar(255),
  "update_by" varchar(255),
  "create_time" timestamp,
  "update_time" timestamp,
  CONSTRAINT "_copy_19" PRIMARY KEY ("dept_id")
);
CREATE INDEX "inx_pid" ON "sys_dept" USING btree (
  "pid" ASC
);
CREATE INDEX "inx_enabled" ON "sys_dept" USING btree (
  "enabled" ASC
);
COMMENT ON COLUMN "sys_dept"."dept_id" IS 'ID';
COMMENT ON COLUMN "sys_dept"."pid" IS '上级部门';
COMMENT ON COLUMN "sys_dept"."sub_count" IS '子部门数目';
COMMENT ON COLUMN "sys_dept"."name" IS '名称';
COMMENT ON COLUMN "sys_dept"."dept_sort" IS '排序';
COMMENT ON COLUMN "sys_dept"."enabled" IS '状态';
COMMENT ON COLUMN "sys_dept"."create_by" IS '创建者';
COMMENT ON COLUMN "sys_dept"."update_by" IS '更新者';
COMMENT ON COLUMN "sys_dept"."create_time" IS '创建日期';
COMMENT ON COLUMN "sys_dept"."update_time" IS '更新时间';
COMMENT ON TABLE "sys_dept" IS '部门';

CREATE TABLE "sys_dict" (
  "dict_id" int8 NOT NULL,
  "name" varchar(255) NOT NULL,
  "description" varchar(255),
  "create_by" varchar(255),
  "update_by" varchar(255),
  "create_time" timestamp,
  "update_time" timestamp,
  CONSTRAINT "_copy_18" PRIMARY KEY ("dict_id")
);
COMMENT ON COLUMN "sys_dict"."dict_id" IS 'ID';
COMMENT ON COLUMN "sys_dict"."name" IS '字典名称';
COMMENT ON COLUMN "sys_dict"."description" IS '描述';
COMMENT ON COLUMN "sys_dict"."create_by" IS '创建者';
COMMENT ON COLUMN "sys_dict"."update_by" IS '更新者';
COMMENT ON COLUMN "sys_dict"."create_time" IS '创建日期';
COMMENT ON COLUMN "sys_dict"."update_time" IS '更新时间';
COMMENT ON TABLE "sys_dict" IS '数据字典';

CREATE TABLE "sys_dict_detail" (
  "detail_id" int8 NOT NULL,
  "dict_id" int8,
  "label" varchar(255) NOT NULL,
  "value" varchar(255) NOT NULL,
  "dict_sort" int4,
  "create_by" varchar(255),
  "update_by" varchar(255),
  "create_time" timestamp,
  "update_time" timestamp,
  CONSTRAINT "_copy_17" PRIMARY KEY ("detail_id")
);
CREATE INDEX "FK5tpkputc6d9nboxojdbgnpmyb" ON "sys_dict_detail" USING btree (
  "dict_id" ASC
);
COMMENT ON COLUMN "sys_dict_detail"."detail_id" IS 'ID';
COMMENT ON COLUMN "sys_dict_detail"."dict_id" IS '字典id';
COMMENT ON COLUMN "sys_dict_detail"."label" IS '字典标签';
COMMENT ON COLUMN "sys_dict_detail"."value" IS '字典值';
COMMENT ON COLUMN "sys_dict_detail"."dict_sort" IS '排序';
COMMENT ON COLUMN "sys_dict_detail"."create_by" IS '创建者';
COMMENT ON COLUMN "sys_dict_detail"."update_by" IS '更新者';
COMMENT ON COLUMN "sys_dict_detail"."create_time" IS '创建日期';
COMMENT ON COLUMN "sys_dict_detail"."update_time" IS '更新时间';
COMMENT ON TABLE "sys_dict_detail" IS '数据字典详情';

CREATE TABLE "sys_job" (
  "job_id" int8 NOT NULL,
  "name" varchar(180) NOT NULL,
  "enabled" varchar(1) NOT NULL,
  "job_sort" int4,
  "create_by" varchar(255),
  "update_by" varchar(255),
  "create_time" timestamp,
  "update_time" timestamp,
  CONSTRAINT "_copy_16" PRIMARY KEY ("job_id")
);
CREATE UNIQUE INDEX "uniq_name" ON "sys_job" USING btree (
  "name" ASC
);
CREATE INDEX "inx_enabled_copy_2" ON "sys_job" USING btree (
  "enabled" ASC
);
COMMENT ON COLUMN "sys_job"."job_id" IS 'ID';
COMMENT ON COLUMN "sys_job"."name" IS '岗位名称';
COMMENT ON COLUMN "sys_job"."enabled" IS '岗位状态';
COMMENT ON COLUMN "sys_job"."job_sort" IS '排序';
COMMENT ON COLUMN "sys_job"."create_by" IS '创建者';
COMMENT ON COLUMN "sys_job"."update_by" IS '更新者';
COMMENT ON COLUMN "sys_job"."create_time" IS '创建日期';
COMMENT ON COLUMN "sys_job"."update_time" IS '更新时间';
COMMENT ON TABLE "sys_job" IS '岗位';

CREATE TABLE "sys_log" (
  "log_id" int8 NOT NULL,
  "description" varchar(255),
  "log_type" varchar(10),
  "method" varchar(255),
  "params" text,
  "request_ip" varchar(255),
  "time" int8,
  "username" varchar(255),
  "address" varchar(255),
  "browser" varchar(255),
  "exception_detail" text,
  "create_time" timestamp,
  CONSTRAINT "_copy_15" PRIMARY KEY ("log_id")
);
CREATE INDEX "log_create_time_index" ON "sys_log" USING btree (
  "create_time" ASC
);
CREATE INDEX "inx_log_type" ON "sys_log" USING btree (
  "log_type" ASC
);
COMMENT ON COLUMN "sys_log"."log_id" IS 'ID';
COMMENT ON TABLE "sys_log" IS '系统日志';

CREATE TABLE "sys_menu" (
  "menu_id" int8 NOT NULL,
  "pid" int8,
  "sub_count" int4,
  "type" int4,
  "title" varchar(100),
  "name" varchar(100),
  "component" varchar(255),
  "menu_sort" int4,
  "icon" varchar(255),
  "path" varchar(255),
  "i_frame" varchar(1),
  "cache" varchar(1),
  "hidden" varchar(1),
  "permission" varchar(255),
  "create_by" varchar(255),
  "update_by" varchar(255),
  "create_time" timestamp,
  "update_time" timestamp,
  CONSTRAINT "_copy_14" PRIMARY KEY ("menu_id")
);
CREATE UNIQUE INDEX "uniq_title" ON "sys_menu" USING btree (
  "title" ASC
);
CREATE UNIQUE INDEX "uniq_name_copy_3" ON "sys_menu" USING btree (
  "name" ASC
);
CREATE INDEX "inx_pid_copy_1" ON "sys_menu" USING btree (
  "pid" ASC
);
COMMENT ON COLUMN "sys_menu"."menu_id" IS 'ID';
COMMENT ON COLUMN "sys_menu"."pid" IS '上级菜单ID';
COMMENT ON COLUMN "sys_menu"."sub_count" IS '子菜单数目';
COMMENT ON COLUMN "sys_menu"."type" IS '菜单类型';
COMMENT ON COLUMN "sys_menu"."title" IS '菜单标题';
COMMENT ON COLUMN "sys_menu"."name" IS '组件名称';
COMMENT ON COLUMN "sys_menu"."component" IS '组件';
COMMENT ON COLUMN "sys_menu"."menu_sort" IS '排序';
COMMENT ON COLUMN "sys_menu"."icon" IS '图标';
COMMENT ON COLUMN "sys_menu"."path" IS '链接地址';
COMMENT ON COLUMN "sys_menu"."i_frame" IS '是否外链';
COMMENT ON COLUMN "sys_menu"."cache" IS '缓存';
COMMENT ON COLUMN "sys_menu"."hidden" IS '隐藏';
COMMENT ON COLUMN "sys_menu"."permission" IS '权限';
COMMENT ON COLUMN "sys_menu"."create_by" IS '创建者';
COMMENT ON COLUMN "sys_menu"."update_by" IS '更新者';
COMMENT ON COLUMN "sys_menu"."create_time" IS '创建日期';
COMMENT ON COLUMN "sys_menu"."update_time" IS '更新时间';
COMMENT ON TABLE "sys_menu" IS '系统菜单';

CREATE TABLE "sys_quartz_job" (
  "job_id" int8 NOT NULL,
  "bean_name" varchar(255),
  "cron_expression" varchar(255),
  "is_pause" varchar(1),
  "job_name" varchar(255),
  "method_name" varchar(255),
  "params" varchar(255),
  "description" varchar(255),
  "person_in_charge" varchar(100),
  "email" varchar(100),
  "sub_task" varchar(100),
  "pause_after_failure" varchar(1),
  "create_by" varchar(255),
  "update_by" varchar(255),
  "create_time" timestamp,
  "update_time" timestamp,
  CONSTRAINT "_copy_13" PRIMARY KEY ("job_id")
);
CREATE INDEX "inx_is_pause" ON "sys_quartz_job" USING btree (
  "is_pause" ASC
);
COMMENT ON COLUMN "sys_quartz_job"."job_id" IS 'ID';
COMMENT ON COLUMN "sys_quartz_job"."bean_name" IS 'Spring Bean名称';
COMMENT ON COLUMN "sys_quartz_job"."cron_expression" IS 'cron 表达式';
COMMENT ON COLUMN "sys_quartz_job"."is_pause" IS '状态：1暂停、0启用';
COMMENT ON COLUMN "sys_quartz_job"."job_name" IS '任务名称';
COMMENT ON COLUMN "sys_quartz_job"."method_name" IS '方法名称';
COMMENT ON COLUMN "sys_quartz_job"."params" IS '参数';
COMMENT ON COLUMN "sys_quartz_job"."description" IS '备注';
COMMENT ON COLUMN "sys_quartz_job"."person_in_charge" IS '负责人';
COMMENT ON COLUMN "sys_quartz_job"."email" IS '报警邮箱';
COMMENT ON COLUMN "sys_quartz_job"."sub_task" IS '子任务ID';
COMMENT ON COLUMN "sys_quartz_job"."pause_after_failure" IS '任务失败后是否暂停';
COMMENT ON COLUMN "sys_quartz_job"."create_by" IS '创建者';
COMMENT ON COLUMN "sys_quartz_job"."update_by" IS '更新者';
COMMENT ON COLUMN "sys_quartz_job"."create_time" IS '创建日期';
COMMENT ON COLUMN "sys_quartz_job"."update_time" IS '更新时间';
COMMENT ON TABLE "sys_quartz_job" IS '定时任务';

CREATE TABLE "sys_quartz_log" (
  "log_id" int8 NOT NULL,
  "bean_name" varchar(255),
  "create_time" timestamp,
  "cron_expression" varchar(255),
  "exception_detail" text,
  "is_success" varchar(1),
  "job_name" varchar(255),
  "method_name" varchar(255),
  "params" varchar(255),
  "time" int8,
  CONSTRAINT "_copy_12" PRIMARY KEY ("log_id")
);
COMMENT ON COLUMN "sys_quartz_log"."log_id" IS 'ID';
COMMENT ON TABLE "sys_quartz_log" IS '定时任务日志';

CREATE TABLE "sys_role" (
  "role_id" int8 NOT NULL,
  "name" varchar(100) NOT NULL,
  "level" int4,
  "description" varchar(255),
  "data_scope" varchar(255),
  "create_by" varchar(255),
  "update_by" varchar(255),
  "create_time" timestamp,
  "update_time" timestamp,
  CONSTRAINT "_copy_11" PRIMARY KEY ("role_id")
);
CREATE UNIQUE INDEX "uniq_name_copy_2" ON "sys_role" USING btree (
  "name" ASC
);
CREATE INDEX "role_name_index" ON "sys_role" USING btree (
  "name" ASC
);
COMMENT ON COLUMN "sys_role"."role_id" IS 'ID';
COMMENT ON COLUMN "sys_role"."name" IS '名称';
COMMENT ON COLUMN "sys_role"."level" IS '角色级别';
COMMENT ON COLUMN "sys_role"."description" IS '描述';
COMMENT ON COLUMN "sys_role"."data_scope" IS '数据权限';
COMMENT ON COLUMN "sys_role"."create_by" IS '创建者';
COMMENT ON COLUMN "sys_role"."update_by" IS '更新者';
COMMENT ON COLUMN "sys_role"."create_time" IS '创建日期';
COMMENT ON COLUMN "sys_role"."update_time" IS '更新时间';
COMMENT ON TABLE "sys_role" IS '角色表';

CREATE TABLE "sys_roles_depts" (
  "role_id" int8 NOT NULL,
  "dept_id" int8 NOT NULL,
  CONSTRAINT "_copy_10" PRIMARY KEY ("role_id", "dept_id")
);
CREATE INDEX "FK7qg6itn5ajdoa9h9o78v9ksur" ON "sys_roles_depts" USING btree (
  "dept_id" ASC
);
COMMENT ON TABLE "sys_roles_depts" IS '角色部门关联';

CREATE TABLE "sys_roles_menus" (
  "menu_id" int8 NOT NULL,
  "role_id" int8 NOT NULL,
  CONSTRAINT "_copy_9" PRIMARY KEY ("menu_id", "role_id")
);
CREATE INDEX "FKcngg2qadojhi3a651a5adkvbq" ON "sys_roles_menus" USING btree (
  "role_id" ASC
);
COMMENT ON COLUMN "sys_roles_menus"."menu_id" IS '菜单ID';
COMMENT ON COLUMN "sys_roles_menus"."role_id" IS '角色ID';
COMMENT ON TABLE "sys_roles_menus" IS '角色菜单关联';

CREATE TABLE "sys_user" (
  "user_id" int8 NOT NULL,
  "dept_id" int8,
  "username" varchar(180),
  "nick_name" varchar(255),
  "gender" varchar(2),
  "phone" varchar(255),
  "email" varchar(180),
  "avatar_name" varchar(255),
  "avatar_path" varchar(255),
  "password" varchar(255),
  "is_admin" varchar(1),
  "enabled" varchar(1),
  "create_by" varchar(255),
  "update_by" varchar(255),
  "pwd_reset_time" timestamp,
  "create_time" timestamp,
  "update_time" timestamp,
  CONSTRAINT "_copy_8" PRIMARY KEY ("user_id")
);
CREATE UNIQUE INDEX "UK_kpubos9gc2cvtkb0thktkbkes" ON "sys_user" USING btree (
  "email" ASC
);
CREATE UNIQUE INDEX "username" ON "sys_user" USING btree (
  "username" ASC
);
CREATE UNIQUE INDEX "uniq_username" ON "sys_user" USING btree (
  "username" ASC
);
CREATE UNIQUE INDEX "uniq_email" ON "sys_user" USING btree (
  "email" ASC
);
CREATE INDEX "FK5rwmryny6jthaaxkogownknqp" ON "sys_user" USING btree (
  "dept_id" ASC
);
CREATE INDEX "inx_enabled_copy_1" ON "sys_user" USING btree (
  "enabled" ASC
);
COMMENT ON COLUMN "sys_user"."user_id" IS 'ID';
COMMENT ON COLUMN "sys_user"."dept_id" IS '部门名称';
COMMENT ON COLUMN "sys_user"."username" IS '用户名';
COMMENT ON COLUMN "sys_user"."nick_name" IS '昵称';
COMMENT ON COLUMN "sys_user"."gender" IS '性别';
COMMENT ON COLUMN "sys_user"."phone" IS '手机号码';
COMMENT ON COLUMN "sys_user"."email" IS '邮箱';
COMMENT ON COLUMN "sys_user"."avatar_name" IS '头像地址';
COMMENT ON COLUMN "sys_user"."avatar_path" IS '头像真实路径';
COMMENT ON COLUMN "sys_user"."password" IS '密码';
COMMENT ON COLUMN "sys_user"."is_admin" IS '是否为admin账号';
COMMENT ON COLUMN "sys_user"."enabled" IS '状态：1启用、0禁用';
COMMENT ON COLUMN "sys_user"."create_by" IS '创建者';
COMMENT ON COLUMN "sys_user"."update_by" IS '更新者';
COMMENT ON COLUMN "sys_user"."pwd_reset_time" IS '修改密码的时间';
COMMENT ON COLUMN "sys_user"."create_time" IS '创建日期';
COMMENT ON COLUMN "sys_user"."update_time" IS '更新时间';
COMMENT ON TABLE "sys_user" IS '系统用户';

CREATE TABLE "sys_users_jobs" (
  "user_id" int8 NOT NULL,
  "job_id" int8 NOT NULL,
  CONSTRAINT "_copy_7" PRIMARY KEY ("user_id", "job_id")
);
COMMENT ON COLUMN "sys_users_jobs"."user_id" IS '用户ID';
COMMENT ON COLUMN "sys_users_jobs"."job_id" IS '岗位ID';

CREATE TABLE "sys_users_roles" (
  "user_id" int8 NOT NULL,
  "role_id" int8 NOT NULL,
  CONSTRAINT "_copy_6" PRIMARY KEY ("user_id", "role_id")
);
CREATE INDEX "FKq4eq273l04bpu4efj0jd0jb98" ON "sys_users_roles" USING btree (
  "role_id" ASC
);
COMMENT ON COLUMN "sys_users_roles"."user_id" IS '用户ID';
COMMENT ON COLUMN "sys_users_roles"."role_id" IS '角色ID';
COMMENT ON TABLE "sys_users_roles" IS '用户角色关联';

CREATE TABLE "tool_alipay_config" (
  "config_id" int8 NOT NULL,
  "app_id" varchar(255),
  "charset" varchar(255),
  "format" varchar(255),
  "gateway_url" varchar(255),
  "notify_url" varchar(255),
  "private_key" text,
  "public_key" text,
  "return_url" varchar(255),
  "sign_type" varchar(255),
  "sys_service_provider_id" varchar(255),
  CONSTRAINT "_copy_5" PRIMARY KEY ("config_id")
);
COMMENT ON COLUMN "tool_alipay_config"."config_id" IS 'ID';
COMMENT ON COLUMN "tool_alipay_config"."app_id" IS '应用ID';
COMMENT ON COLUMN "tool_alipay_config"."charset" IS '编码';
COMMENT ON COLUMN "tool_alipay_config"."format" IS '类型 固定格式json';
COMMENT ON COLUMN "tool_alipay_config"."gateway_url" IS '网关地址';
COMMENT ON COLUMN "tool_alipay_config"."notify_url" IS '异步回调';
COMMENT ON COLUMN "tool_alipay_config"."private_key" IS '私钥';
COMMENT ON COLUMN "tool_alipay_config"."public_key" IS '公钥';
COMMENT ON COLUMN "tool_alipay_config"."return_url" IS '回调地址';
COMMENT ON COLUMN "tool_alipay_config"."sign_type" IS '签名方式';
COMMENT ON COLUMN "tool_alipay_config"."sys_service_provider_id" IS '商户号';
COMMENT ON TABLE "tool_alipay_config" IS '支付宝配置类';

CREATE TABLE "tool_email_config" (
  "config_id" int8 NOT NULL,
  "from_user" varchar(255),
  "host" varchar(255),
  "pass" varchar(255),
  "port" varchar(255),
  "user" varchar(255),
  CONSTRAINT "_copy_4" PRIMARY KEY ("config_id")
);
COMMENT ON COLUMN "tool_email_config"."config_id" IS 'ID';
COMMENT ON COLUMN "tool_email_config"."from_user" IS '收件人';
COMMENT ON COLUMN "tool_email_config"."host" IS '邮件服务器SMTP地址';
COMMENT ON COLUMN "tool_email_config"."pass" IS '密码';
COMMENT ON COLUMN "tool_email_config"."port" IS '端口';
COMMENT ON COLUMN "tool_email_config"."user" IS '发件者用户名';
COMMENT ON TABLE "tool_email_config" IS '邮箱配置';

CREATE TABLE "tool_local_storage" (
  "storage_id" int8 NOT NULL,
  "real_name" varchar(255),
  "name" varchar(255),
  "suffix" varchar(255),
  "path" varchar(255),
  "type" varchar(255),
  "size" varchar(100),
  "create_by" varchar(255),
  "update_by" varchar(255),
  "create_time" timestamp,
  "update_time" timestamp,
  CONSTRAINT "_copy_3" PRIMARY KEY ("storage_id")
);
COMMENT ON COLUMN "tool_local_storage"."storage_id" IS 'ID';
COMMENT ON COLUMN "tool_local_storage"."real_name" IS '文件真实的名称';
COMMENT ON COLUMN "tool_local_storage"."name" IS '文件名';
COMMENT ON COLUMN "tool_local_storage"."suffix" IS '后缀';
COMMENT ON COLUMN "tool_local_storage"."path" IS '路径';
COMMENT ON COLUMN "tool_local_storage"."type" IS '类型';
COMMENT ON COLUMN "tool_local_storage"."size" IS '大小';
COMMENT ON COLUMN "tool_local_storage"."create_by" IS '创建者';
COMMENT ON COLUMN "tool_local_storage"."update_by" IS '更新者';
COMMENT ON COLUMN "tool_local_storage"."create_time" IS '创建日期';
COMMENT ON COLUMN "tool_local_storage"."update_time" IS '更新时间';
COMMENT ON TABLE "tool_local_storage" IS '本地存储';

CREATE TABLE "tool_qiniu_config" (
  "config_id" int8 NOT NULL,
  "access_key" text,
  "bucket" varchar(255),
  "host" varchar(255) NOT NULL,
  "secret_key" text,
  "type" varchar(255),
  "zone" varchar(255),
  CONSTRAINT "_copy_2" PRIMARY KEY ("config_id")
);
COMMENT ON COLUMN "tool_qiniu_config"."config_id" IS 'ID';
COMMENT ON COLUMN "tool_qiniu_config"."access_key" IS 'accessKey';
COMMENT ON COLUMN "tool_qiniu_config"."bucket" IS 'Bucket 识别符';
COMMENT ON COLUMN "tool_qiniu_config"."host" IS '外链域名';
COMMENT ON COLUMN "tool_qiniu_config"."secret_key" IS 'secretKey';
COMMENT ON COLUMN "tool_qiniu_config"."type" IS '空间类型';
COMMENT ON COLUMN "tool_qiniu_config"."zone" IS '机房';
COMMENT ON TABLE "tool_qiniu_config" IS '七牛云配置';

CREATE TABLE "tool_qiniu_content" (
  "content_id" int8 NOT NULL,
  "bucket" varchar(255),
  "name" varchar(180),
  "size" varchar(255),
  "type" varchar(255),
  "url" varchar(255),
  "suffix" varchar(255),
  "update_time" timestamp,
  CONSTRAINT "_copy_1" PRIMARY KEY ("content_id")
);
CREATE UNIQUE INDEX "uniq_name_copy_1" ON "tool_qiniu_content" USING btree (
  "name" ASC
);
COMMENT ON COLUMN "tool_qiniu_content"."content_id" IS 'ID';
COMMENT ON COLUMN "tool_qiniu_content"."bucket" IS 'Bucket 识别符';
COMMENT ON COLUMN "tool_qiniu_content"."name" IS '文件名称';
COMMENT ON COLUMN "tool_qiniu_content"."size" IS '文件大小';
COMMENT ON COLUMN "tool_qiniu_content"."type" IS '文件类型：私有或公开';
COMMENT ON COLUMN "tool_qiniu_content"."url" IS '文件url';
COMMENT ON COLUMN "tool_qiniu_content"."suffix" IS '文件后缀';
COMMENT ON COLUMN "tool_qiniu_content"."update_time" IS '上传或同步的时间';
COMMENT ON TABLE "tool_qiniu_content" IS '七牛云文件存储';

