
use fengapi;
-- 接口信息
create table if not exists fengapi.`interface_info`
(
`id` bigint not null auto_increment comment '主键' primary key,
`name` varchar(256) not null comment '名称',
`description` varchar(256) null comment '描述',
`url` varchar(512) not null comment '接口地址',
`requestParams` text null comment '请求参数',
`requestHeader` text null comment '请求头',
`responseHeader` text null comment '响应头',
`status` int default 0 not null comment '接口状态（0-关闭，1-开启）',
`method` varchar(256) not null comment '请求类型',
`userId` bigint not null comment '创建人',
`createTime` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
`updateTime` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
`isDelete` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
) comment '接口信息';

insert into fengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('刘笑愚', '吴琪', 'www.wesley-trantow.com', '万鸿煊', '王越彬', 0, '黄雪松', 26826);
insert into fengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('潘展鹏', '叶晓啸', 'www.courtney-kassulke.net', '戴鸿煊', '袁荣轩', 0, '谢立诚', 434);
insert into fengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('覃天宇', '冯昊强', 'www.johnie-harris.name', '武博文', '戴思聪', 0, '毛昊焱', 2334);
insert into fengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('谭黎昕', '傅哲瀚', 'www.josette-adams.org', '覃振家', '吕风华', 0, '孔鹭洋', 57553);
insert into fengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('魏嘉熙', '沈思聪', 'www.wyatt-nader.org', '韩嘉懿', '熊思源', 0, '姚立轩', 0);
insert into fengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('龚修洁', '宋锦程', 'www.jerlene-grimes.io', '廖哲瀚', '张建辉', 0, '林天宇', 34618);
insert into fengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('蒋鑫磊', '谭明辉', 'www.micki-dicki.name', '唐雪松', '沈鹏飞', 0, '罗烨华', 27);
insert into fengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('钱雪松', '吕鹏', 'www.andy-russel.org', '范烨伟', '邵黎昕', 0, '苏笑愚', 5460331959);
insert into fengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('严晟睿', '唐晟睿', 'www.nadine-bradtke.name', '郑峻熙', '冯琪', 0, '秦烨华', 90477376);
insert into fengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('段浩轩', '潘文博', 'www.terrence-konopelski.co', '韩雨泽', '袁志强', 0, '陈博文', 9453614492);
insert into fengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('黎健雄', '龙泽洋', 'www.rodney-douglas.io', '冯晟睿', '韩明哲', 0, '蒋弘文', 70703);
insert into fengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('邵绍齐', '范振家', 'www.wilbur-reinger.name', '邹绍辉', '叶哲瀚', 0, '姚子轩', 24180);
insert into fengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('顾博文', '张瑾瑜', 'www.kittie-kautzer.name', '龙修杰', '万弘文', 0, '潘弘文', 334268466);
insert into fengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('唐明辉', '郝耀杰', 'www.ed-barton.org', '蒋晓啸', '段钰轩', 0, '程明', 6);
insert into fengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('陶泽洋', '龙语堂', 'www.shane-braun.io', '杜驰', '徐笑愚', 0, '熊展鹏', 8);
insert into fengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('孔炎彬', '龚昊天', 'www.rolf-wiegand.net', '赵明辉', '覃昊天', 0, '白天宇', 7453201);
insert into fengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('陈梓晨', '高志泽', 'www.norah-goldner.org', '何鹤轩', '郝鹏', 0, '李煜城', 791);
insert into fengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('白思', '汪懿轩', 'www.hugo-bradtke.co', '于立轩', '毛楷瑞', 0, '罗俊驰', 3219);
insert into fengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('李琪', '谭健雄', 'www.gerry-dicki.biz', '龙果', '吴晟睿', 0, '马昊焱', 61151986);
insert into fengapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('吴思', '吴志泽', 'www.kareem-feest.io', '汪黎昕', '赵瑾瑜', 0, '邱致远', 6);

-- 用户调用接口关系表
create table if not exists fengapi.`user_interface_info`
(
    `id` bigint not null auto_increment comment '主键' primary key,
    `userId` bigint not null comment '调用用户 id',
    `interfaceInfoId` bigint not null comment '接口 id',
    `totalNum` int default 0 not null comment '总调用次数',
    `leftNum` int default 0 not null comment '剩余调用次数',
    `status` int default 0 not null comment '0-正常，1-禁用',
    `createTime` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `updateTime` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `isDelete` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
) comment '用户调用接口关系';

create table if not exists fengapi.`rustic_love_words`
(
    `id` bigint not null auto_increment comment '主键' primary key,
    `content` varchar(256) null comment '土味情话内容',
    `userId` bigint not null comment '创建人',
    `createTime` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `updateTime` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `isDelete` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
) comment '土味情话表';


insert into fengapi.`rustic_love_words` (`content`, `userId`) values ('不要抱怨，抱我。', 1);
insert into fengapi.`rustic_love_words` (`content`, `userId`) values ('最近有谣言说我喜欢你，我要澄清一下，那不是谣言。', 1);
insert into fengapi.`rustic_love_words` (`content`, `userId`) values ('我怀疑你的本质是一本书，不然为什么让我越看越想睡', 1);
insert into fengapi.`rustic_love_words` (`content`, `userId`) values ('你知道我的缺点是什么吗？ 是缺点你', 1);
insert into fengapi.`rustic_love_words` (`content`, `userId`) values ('我发现昨天很喜欢你，今天也很喜欢你，而且有预感明天也会喜欢你。', 1);
insert into fengapi.`rustic_love_words` (`content`, `userId`) values ('“你最近是不是又胖了？”“没有啊，为什么这么说？”“那为什么在我心里的分量越来越重了？”', 1);
insert into fengapi.`rustic_love_words` (`content`, `userId`) values ('近朱者赤，近你者甜', 1);
insert into fengapi.`rustic_love_words` (`content`, `userId`) values ('我是九你是三，除了你还是你', 1);
insert into fengapi.`rustic_love_words` (`content`, `userId`) values ('“我觉得你这个人不适合谈恋爱”“为什么？”“适合结婚。”', 1);
insert into fengapi.`rustic_love_words` (`content`, `userId`) values ('你知道你和星星有什么区别吗？星星在天上，你在我心里。', 1);
insert into fengapi.`rustic_love_words` (`content`, `userId`) values ('这是我的手背，这是我的脚背，你是我的宝贝。', 1);
insert into fengapi.`rustic_love_words` (`content`, `userId`) values ('猜猜我的心在哪边？左边，错了，在你那边。', 1);
insert into fengapi.`rustic_love_words` (`content`, `userId`) values ('听闻先生治家有方，小女余生愿闻其详', 1);
insert into fengapi.`rustic_love_words` (`content`, `userId`) values ('你闻到什么味道了吗？没有啊，怎么你一出来空气都是甜的了', 1);
insert into fengapi.`rustic_love_words` (`content`, `userId`) values ('“我想买一块地。”“什么地？”“你的死心塌地。”', 1);
insert into fengapi.`rustic_love_words` (`content`, `userId`) values ('“你累不累啊？”“不累。”“可是你都在我脑里跑了一天了”', 1);
insert into fengapi.`rustic_love_words` (`content`, `userId`) values ('“你能不能闭嘴？”“我没有说话啊”“那为什么我满脑子都是你的声音？”', 1);
insert into fengapi.`rustic_love_words` (`content`, `userId`) values ('“你知道我为什么感冒了吗？”“因为着凉了？”“不，因为我对你完全没有抵抗力。”', 1);
insert into fengapi.`rustic_love_words` (`content`, `userId`) values ('甜有100种方式，吃糖，蛋糕，还有每天98次的想你', 1);
insert into fengapi.`rustic_love_words` (`content`, `userId`) values ('从今以后我只能称呼你为您了，因为，你在我心上。', 1);
insert into fengapi.`rustic_love_words` (`content`, `userId`) values ('莫文蔚的阴天，孙燕姿的雨天周杰伦的晴天，都不如你和我聊天', 1);
insert into fengapi.`rustic_love_words` (`content`, `userId`) values ('你有没有问到什么烧焦的味道？那是我的心在燃烧', 1);
insert into fengapi.`rustic_love_words` (`content`, `userId`) values ('“你为什么要害我？”“害你什么？”“害我那么喜欢你！”', 1);
insert into fengapi.`rustic_love_words` (`content`, `userId`) values ('我对你的爱，就像拖拉机上山，轰轰烈烈……', 1);
insert into fengapi.`rustic_love_words` (`content`, `userId`) values ('“游乐园那个，可以骑在上面的”“有音乐的叫旋转什么？”“木马。”“mua”', 1);
insert into fengapi.`rustic_love_words` (`content`, `userId`) values ('“面对你，我不仅善解人意，我还善解人衣。”', 1);
insert into fengapi.`rustic_love_words` (`content`, `userId`) values ('你喜欢喝水吗？那你已经喜欢上70%的我了。', 1);
insert into fengapi.`rustic_love_words` (`content`, `userId`) values ('“既然你把我的心已经弄乱了，那你打算什么时候来弄乱我的床？”', 1);
insert into fengapi.`rustic_love_words` (`content`, `userId`) values ('你上辈子一定是碳酸饮料吧，为什么我一看到你就能开心的冒泡。', 1);
insert into fengapi.`rustic_love_words` (`content`, `userId`) values ('三十晚上的鞭炮再响，都没有我想想你那么想。', 1);
insert into fengapi.`rustic_love_words` (`content`, `userId`) values ('我想去取一下东西你等一下我来娶你了。', 1);
insert into fengapi.`rustic_love_words` (`content`, `userId`) values ('别让我看见你不然我见你一次就喜欢你一次。', 1);
insert into fengapi.`rustic_love_words` (`content`, `userId`) values ('你知道你像什么人吗？（什么人？）我的女人。', 1);
insert into fengapi.`rustic_love_words` (`content`, `userId`) values ('你近视吗？（不近视啊）那你怎么看不出我喜欢你。', 1);
insert into fengapi.`rustic_love_words` (`content`, `userId`) values ('现在几点了？（12点）不，是我们幸福的起点 。', 1);
insert into fengapi.`rustic_love_words` (`content`, `userId`) values ('把你的名字写在烟上吸进肺里，让你保持在离我心脏最近的地方 。', 1);
insert into fengapi.`rustic_love_words` (`content`, `userId`) values ('我生在南方，活在南方，栽在你手里，总算是去过不一样的地方 。', 1);
insert into fengapi.`rustic_love_words` (`content`, `userId`) values ('我想吃碗面 什么面 你的心里面。', 1);
