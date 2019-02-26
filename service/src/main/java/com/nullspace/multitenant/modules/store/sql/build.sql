create table currencies
(
	id bigserial not null
		constraint currencies_pkey
			primary key,
	currency_code varchar(255)
		constraint uk_jekn45c17p62ja9i4g7xj1st8
			unique,
	currency_currency_code varchar(255) not null
		constraint uk_o10hj594ximk9mmmwbnnajwsp
			unique,
	currency_name varchar(255)
		constraint uk_8mramfdinct40l3hsd15fob3i
			unique,
	currency_supported boolean
);

alter table currencies owner to postgres;

create table geozones
(
	id bigserial not null
		constraint geozones_pkey
			primary key,
	geozone_code varchar(255),
	geozone_name varchar(255)
);

alter table geozones owner to postgres;

create table countries
(
	id bigserial not null
		constraint countries_pkey
			primary key,
	country_isocode varchar(255) not null
		constraint uk_3kh9cvjudsni11ybj567qyw9j
			unique,
	country_supported boolean,
	geozone_id bigint
		constraint fk4xerdwb314nwnkk6enx3yulb8
			references geozones
);

alter table countries owner to postgres;

create table country_descriptions
(
	id bigserial not null
		constraint country_descriptions_pkey
			primary key,
	description text,
	name varchar(120) not null,
	title varchar(100),
	country_id bigint not null
		constraint uklsos5j2orav8tcfvjh9d23yyw
			unique
		constraint fkcf1htev87ikaaeyg3hg11rd1e
			references countries
);

alter table country_descriptions owner to postgres;

create table geozone_descriptions
(
	id bigserial not null
		constraint geozone_descriptions_pkey
			primary key,
	description text,
	name varchar(120) not null,
	title varchar(100),
	geozone_id bigint
		constraint uk8mss7dwngkamj7l9isd62x01r
			unique
		constraint fkga117sxsuucdna8e086irllth
			references geozones
);

alter table geozone_descriptions owner to postgres;

create table module_configurations
(
	id bigserial not null
		constraint module_configurations_pkey
			primary key,
	code varchar(255) not null,
	details text,
	configuration text,
	custom_ind boolean,
	image varchar(255),
	module varchar(255),
	regions varchar(255),
	type varchar(255)
);

alter table module_configurations owner to postgres;

create table permissions
(
	id bigserial not null
		constraint permissions_pkey
			primary key,
	permission_name varchar(255)
		constraint uk_nry1f3jmc4abb5yvkftlvn6vg
			unique
);

alter table permissions owner to postgres;

create table product_types
(
	id bigserial not null
		constraint product_types_pkey
			primary key,
	prd_type_add_to_cart boolean,
	prd_type_code varchar(255)
);

alter table product_types owner to postgres;

create table sm_groups
(
	id bigserial not null
		constraint sm_groups_pkey
			primary key,
	group_name varchar(255)
		constraint uk_j4rjrtqdtjdv8q4ie9qjvd898
			unique,
	group_type varchar(255)
);

alter table sm_groups owner to postgres;

create table permission_groups
(
	permission_id bigint not null
		constraint fk7dd1htxv37975f87851wig2gr
			references permissions,
	group_id bigint not null
		constraint fkdgm3c44bb61h2lgxfr4pggfet
			references sm_groups
);

alter table permission_groups owner to postgres;

create table system_configurations
(
	id bigserial not null
		constraint system_configurations_pkey
			primary key,
	config_key varchar(255),
	value varchar(255)
);

alter table system_configurations owner to postgres;

create table zones
(
	id bigserial not null
		constraint zones_pkey
			primary key,
	zone_code varchar(255) not null
		constraint uk_d6t3rii531bw5d101rhikh8jp
			unique,
	country_id bigint not null
		constraint fkiw7sl3f316mtgydrumesg2ind
			references countries
);

alter table zones owner to postgres;

create table merchant_stores
(
	id bigserial not null
		constraint merchant_stores_pkey
			primary key,
	store_code varchar(100) not null
		constraint uk_a3oyltm3jtddgrlelnxtoov05
			unique,
	continueshoppingurl varchar(150),
	currency_format_national boolean,
	domain_name varchar(80),
	in_business_since date,
	invoice_template varchar(25),
	seizeunitcode varchar(5),
	store_email varchar(60) not null,
	store_logo varchar(100),
	store_template varchar(25),
	store_address varchar(255),
	store_city varchar(100),
	store_name varchar(100) not null,
	store_phone varchar(50),
	store_postal_code varchar(15),
	store_state_prov varchar(100),
	use_cache boolean,
	weightunitcode varchar(5),
	country_id bigint not null
		constraint fkouwfr6baxmfgsjnq5eb3ocqs7
			references countries,
	currency_id bigint not null
		constraint fks7lv37oixfq7yxuge8hywtadr
			references currencies,
	zone_id bigint
		constraint fk6b2wbugh72dw7haj3bychepo0
			references zones
);

alter table merchant_stores owner to postgres;

create table categories
(
	id bigserial not null
		constraint categories_pkey
			primary key,
	category_image varchar(100),
	category_status boolean,
	code varchar(100) not null,
	depth integer,
	featured boolean,
	lineage varchar(255),
	sort_order integer,
	visible boolean,
	merchant_id bigint not null
		constraint fkki77344i8w8f8aevrb9xmju2w
			references merchant_stores,
	parent_id bigint
		constraint fksaok720gsu4u2wrgbk10b5n8d
			references categories,
	constraint ukdo2wcbftm82lobnxkkj63qq9u
		unique (merchant_id, code)
);

alter table categories owner to postgres;

create table category_descriptions
(
	id bigserial not null
		constraint category_descriptions_pkey
			primary key,
	description text,
	name varchar(120) not null,
	title varchar(100),
	category_highlight varchar(255),
	meta_description varchar(255),
	meta_keywords varchar(255),
	meta_title varchar(120),
	sef_url varchar(120),
	category_id bigint not null
		constraint ukaqn7yc61enmgqu3pf1s4jjhb1
			unique
		constraint fkch10o5wqmvlq3ysnow4n55qlg
			references categories
);

alter table category_descriptions owner to postgres;

create table customer_option_values
(
	id bigserial not null
		constraint customer_option_values_pkey
			primary key,
	customer_opt_val_code varchar(255),
	customer_opt_val_image varchar(255),
	sort_order integer,
	merchant_id bigint not null
		constraint fkncu3rwh9cyskk7nhkkb198t56
			references merchant_stores,
	constraint ukef8e77ab0jrfq926eprnkcpkp
		unique (merchant_id, customer_opt_val_code)
);

alter table customer_option_values owner to postgres;

create table customer_opt_val_descriptions
(
	id bigserial not null
		constraint customer_opt_val_descriptions_pkey
			primary key,
	description text,
	name varchar(120) not null,
	title varchar(100),
	customer_opt_val_id bigint
		constraint ukff6wsixbrvljgh01gqwqjtfbw
			unique
		constraint fkifl32su7slsu7l045ihsfjyfx
			references customer_option_values
);

alter table customer_opt_val_descriptions owner to postgres;

create index cust_opt_val_code_idx
	on customer_option_values (customer_opt_val_code);

create table customer_options
(
	id bigserial not null
		constraint customer_options_pkey
			primary key,
	customer_opt_active boolean,
	customer_opt_code varchar(255),
	customer_option_type varchar(10),
	customer_opt_public boolean,
	sort_order integer,
	merchant_id bigint not null
		constraint fkec0ece73nanf65ju174531sy8
			references merchant_stores,
	constraint uk55l6g091pgh5c2mboti4a1wv4
		unique (merchant_id, customer_opt_code)
);

alter table customer_options owner to postgres;

create table customer_option_descs
(
	id bigserial not null
		constraint customer_option_descs_pkey
			primary key,
	customer_option_comment text,
	customer_option_id bigint not null
		constraint ukbmwggnk8qmmj77lrhhh3965oq
			unique
		constraint fkxrmbogls52343chip3xtgdq6
			references customer_options
);

alter table customer_option_descs owner to postgres;

create table customer_option_sets
(
	id bigserial not null
		constraint customer_option_sets_pkey
			primary key,
	sort_order integer,
	customer_option_id bigint not null
		constraint fk20f6jjs4s0qs97bgts1ufgty6
			references customer_options,
	customer_option_value_id bigint not null
		constraint fk82dxsb108c10xhdoxmtwvjucn
			references customer_option_values,
	constraint ukh14551kx2n6tiu51x6i0kgnax
		unique (customer_option_id, customer_option_value_id)
);

alter table customer_option_sets owner to postgres;

create index cust_opt_code_idx
	on customer_options (customer_opt_code);

create table customers
(
	id bigserial not null
		constraint customers_pkey
			primary key,
	customer_anonymous boolean,
	billing_street_address varchar(256),
	billing_city varchar(100),
	billing_company varchar(100),
	billing_first_name varchar(64) not null,
	billing_last_name varchar(64) not null,
	latitude varchar(100),
	longitude varchar(100),
	billing_postcode varchar(20),
	billing_state varchar(100),
	billing_telephone varchar(32),
	customer_company varchar(100),
	review_avg numeric(19,2),
	review_count integer,
	customer_dob timestamp,
	delivery_street_address varchar(256),
	delivery_city varchar(100),
	delivery_company varchar(100),
	delivery_first_name varchar(64),
	delivery_last_name varchar(64),
	delivery_postcode varchar(20),
	delivery_state varchar(100),
	delivery_telephone varchar(32),
	customer_email_address varchar(96) not null,
	customer_gender varchar(1),
	customer_nick varchar(96),
	customer_password varchar(60),
	provider varchar(255),
	billing_country_id bigint not null
		constraint fk9kd4vqcloiym6pu7nxfnfexdc
			references countries,
	billing_zone_id bigint
		constraint fknucy35dp2w52ax4ue3quqpymw
			references zones,
	delivery_country_id bigint
		constraint fk8biigem125y99d0mt9c5kuq6e
			references countries,
	delivery_zone_id bigint
		constraint fkb7aayf79f35nv8fnawbnayrmn
			references zones,
	merchant_id bigint not null
		constraint fk573kgu16i9atex5rqwl3740wd
			references merchant_stores
);

alter table customers owner to postgres;

create table customer_attributes
(
	id bigserial not null
		constraint customer_attributes_pkey
			primary key,
	customer_attr_txt_val varchar(255),
	customer_id bigint not null
		constraint fks45mkj5r1cqgacie7xswqlei3
			references customers,
	option_id bigint not null
		constraint fkjsom5vv2d3n26uhnjohohy25x
			references customer_options,
	option_value_id bigint not null
		constraint fkg94bqihrrh8vhasn4rtfw6v56
			references customer_option_values,
	constraint ukrin0rat2ag8egnno401x5v3s6
		unique (option_id, customer_id)
);

alter table customer_attributes owner to postgres;

create table customer_groups
(
	customer_id bigint not null
		constraint fkh4q8dly5t2ef0vc2qi71a279a
			references customers,
	group_id bigint not null
		constraint fka127gr19k5cv8j7ijad6kw3a2
			references sm_groups
);

alter table customer_groups owner to postgres;

create table customer_reviews
(
	id bigserial not null
		constraint customer_reviews_pkey
			primary key,
	review_date timestamp,
	reviews_rating double precision,
	reviews_read bigint,
	status integer,
	customers_id bigint
		constraint fkt6ueu888iuacqhbvcjj35dnju
			references customers,
	reviewed_customer_id bigint
		constraint fkho6py2v7itc714t3canjgse5b
			references customers,
	constraint ukhyyh57efhoa70awt7xgxyxr0y
		unique (customers_id, reviewed_customer_id)
);

alter table customer_reviews owner to postgres;

create table customer_review_descriptions
(
	id bigserial not null
		constraint customer_review_descriptions_pkey
			primary key,
	description text,
	name varchar(120) not null,
	title varchar(100),
	customer_review_id bigint
		constraint ukn704mxqw5e68fisjll4ja3dn
			unique
		constraint fkk59fbgh66nfkrv74qpncg2h7i
			references customer_reviews
);

alter table customer_review_descriptions owner to postgres;

create table file_histories
(
	id bigserial not null
		constraint file_histories_pkey
			primary key,
	accounted_date timestamp,
	date_added timestamp not null,
	date_deleted timestamp,
	download_count integer not null,
	file_id bigint,
	filesize integer not null,
	merchant_id bigint not null
		constraint fkowsx641m68t4m91fq9imep8pf
			references merchant_stores,
	constraint ukf79es5m0yxjo1pxv7y4idn2lm
		unique (merchant_id, file_id)
);

alter table file_histories owner to postgres;

create table manufacturers
(
	id bigserial not null
		constraint manufacturers_pkey
			primary key,
	code varchar(100) not null,
	manufacturer_image varchar(255),
	sort_order integer,
	merchant_id bigint not null
		constraint fkjpgxyngl5ix9yqio1q24w22ge
			references merchant_stores,
	constraint ukqs7q7e9vj0dvr1kmptr1109hd
		unique (merchant_id, code)
);

alter table manufacturers owner to postgres;

create table manufacturer_descriptions
(
	id bigserial not null
		constraint manufacturer_descriptions_pkey
			primary key,
	description text,
	name varchar(120) not null,
	title varchar(100),
	date_last_click timestamp,
	manufacturers_url varchar(255),
	url_clicked integer,
	manufacturer_id bigint not null
		constraint ukq8jatf1dsgyx71ni00yo44mkd
			unique
		constraint fkmd8nx4yiurfqnlahf4b4nflw3
			references manufacturers
);

alter table manufacturer_descriptions owner to postgres;

create table merchant_configurations
(
	id bigserial not null
		constraint merchant_configurations_pkey
			primary key,
	config_key varchar(255),
	type varchar(255),
	value text,
	merchant_id bigint
		constraint fk2uea46iuuulrifbxuy3riwxcd
			references merchant_stores,
	constraint ukb56s700sugr97wn3f116wqq66
		unique (merchant_id, config_key)
);

alter table merchant_configurations owner to postgres;

create table merchant_logs
(
	id bigserial not null
		constraint merchant_logs_pkey
			primary key,
	log text,
	module varchar(25),
	merchant_id bigint not null
		constraint fk7rcp693logfj1wash7wxsu85h
			references merchant_stores
);

alter table merchant_logs owner to postgres;

create table orders
(
	id bigserial not null
		constraint orders_pkey
			primary key,
	billing_street_address varchar(256),
	billing_city varchar(100),
	billing_company varchar(100),
	billing_first_name varchar(64) not null,
	billing_last_name varchar(64) not null,
	latitude varchar(100),
	longitude varchar(100),
	billing_postcode varchar(20),
	billing_state varchar(100),
	billing_telephone varchar(32),
	channel varchar(255),
	confirmed_address boolean,
	card_type varchar(255),
	cc_cvv varchar(255),
	cc_expires varchar(255),
	cc_number varchar(255),
	cc_owner varchar(255),
	currency_value numeric(19,2),
	customer_agreed boolean,
	customer_email_address varchar(50) not null,
	customer_id bigint,
	date_purchased date,
	delivery_street_address varchar(256),
	delivery_city varchar(100),
	delivery_company varchar(100),
	delivery_first_name varchar(64),
	delivery_last_name varchar(64),
	delivery_postcode varchar(20),
	delivery_state varchar(100),
	delivery_telephone varchar(32),
	ip_address varchar(255),
	last_modified timestamp,
	locale varchar(255),
	order_date_finished timestamp,
	order_type varchar(255),
	payment_module_code varchar(255),
	payment_type varchar(255),
	shipping_module_code varchar(255),
	order_status varchar(255),
	order_total numeric(19,2),
	billing_country_id bigint not null
		constraint fkrnyp48tijgg46leud74ddls4a
			references countries,
	billing_zone_id bigint
		constraint fk8csxq00vg9djcy36p8flal1m1
			references zones,
	currency_id bigint
		constraint fk7bfxsddnhhd9wliyy7o6ehnl7
			references currencies,
	delivery_country_id bigint
		constraint fkbehc37w4vmo6dt8dy4xtyb4l3
			references countries,
	delivery_zone_id bigint
		constraint fkrpbh6n5m7bs1plnkwkx262cs5
			references zones,
	merchant_id bigint
		constraint fkbpw8r4q9sp2yuky0op99o0loo
			references merchant_stores
);

alter table orders owner to postgres;

create table order_accounts
(
	id bigserial not null
		constraint order_accounts_pkey
			primary key,
	order_account_bill_day integer not null,
	order_account_end_date date,
	order_account_start_date date not null,
	order_id bigint not null
		constraint fk1wbw6fg3s9bvbdwcgybrwck71
			references orders
);

alter table order_accounts owner to postgres;

create table order_attributes
(
	id bigserial not null
		constraint order_attributes_pkey
			primary key,
	identifier varchar(255) not null,
	value varchar(255) not null,
	order_id bigint not null
		constraint fk3uvo7xfxjotsfe83ypr7yl1al
			references orders
);

alter table order_attributes owner to postgres;

create table order_products
(
	id bigserial not null
		constraint order_products_pkey
			primary key,
	onetime_charge numeric(19,2) not null,
	product_name varchar(64) not null,
	product_quantity integer,
	product_sku varchar(255),
	order_id bigint not null
		constraint fkawxpt1ns1sr7al76nvjkv21of
			references orders
);

alter table order_products owner to postgres;

create table order_account_products
(
	id bigserial not null
		constraint order_account_products_pkey
			primary key,
	order_account_product_accnt_dt date,
	order_account_product_end_dt date,
	order_account_product_eot timestamp,
	order_account_product_l_st_dt timestamp,
	order_account_product_l_trx_st integer not null,
	order_account_product_pm_fr_ty integer not null,
	order_account_product_st_dt date not null,
	order_account_product_status integer not null,
	order_account_id bigint not null
		constraint fkb4v90pmt1aeq5t1tuo27qmhil
			references order_accounts,
	order_product_id bigint not null
		constraint fkle7v163sytwfwjjixbhsv6bnh
			references order_products
);

alter table order_account_products owner to postgres;

create table order_product_attributes
(
	id bigserial not null
		constraint order_product_attributes_pkey
			primary key,
	product_attribute_is_free boolean not null,
	product_attribute_name varchar(255),
	product_attribute_price numeric(15,4) not null,
	product_attribute_val_name varchar(255),
	product_attribute_weight numeric(15,4),
	product_option_id bigint not null,
	product_option_value_id bigint not null,
	order_product_id bigint not null
		constraint fk8pewh7xc03lpju785vj5t39vi
			references order_products
);

alter table order_product_attributes owner to postgres;

create table order_product_downloads
(
	id bigserial not null
		constraint order_product_downloads_pkey
			primary key,
	download_count integer not null,
	download_maxdays integer not null,
	order_product_filename varchar(255) not null,
	order_product_id bigint not null
		constraint fk6smq4r1ksu9h9xwaklvp2bb43
			references order_products
);

alter table order_product_downloads owner to postgres;

create table order_product_prices
(
	id bigserial not null
		constraint order_product_prices_pkey
			primary key,
	default_price boolean not null,
	product_price numeric(19,2) not null,
	product_price_code varchar(64) not null,
	product_price_name varchar(255),
	product_price_special numeric(19,2),
	prd_price_special_end_dt timestamp,
	prd_price_special_st_dt timestamp,
	order_product_id bigint not null
		constraint fknrcd1jef93wx74w9qismmboj
			references order_products
);

alter table order_product_prices owner to postgres;

create table order_status_histories
(
	id bigserial not null
		constraint order_status_histories_pkey
			primary key,
	comments text,
	customer_notified integer,
	date_added timestamp not null,
	status varchar(255),
	order_id bigint not null
		constraint fkgair3047h1rin6u8y0fvc1fg5
			references orders
);

alter table order_status_histories owner to postgres;

create table order_totals
(
	id bigserial not null
		constraint order_totals_pkey
			primary key,
	module varchar(60),
	code varchar(255) not null,
	order_total_type varchar(255),
	order_value_type varchar(255),
	sort_order integer not null,
	text text,
	title varchar(255),
	value numeric(15,4) not null,
	order_id bigint not null
		constraint fk8nvxhhhu1yhll40dnmuygjl6o
			references orders
);

alter table order_totals owner to postgres;

create table product_option_values
(
	id bigserial not null
		constraint product_option_values_pkey
			primary key,
	product_option_val_code varchar(255),
	product_opt_for_disp boolean,
	product_opt_val_image varchar(255),
	product_opt_val_sort_ord integer,
	merchant_id bigint not null
		constraint fk7jyc7rqbckm8wfxi5qiasucr9
			references merchant_stores,
	constraint uk3djd3cjq46841sni3dxd2thcj
		unique (merchant_id, product_option_val_code)
);

alter table product_option_values owner to postgres;

create table product_option_value_descriptions
(
	id bigserial not null
		constraint product_option_value_descriptions_pkey
			primary key,
	description text,
	name varchar(120) not null,
	title varchar(100),
	product_option_value_id bigint
		constraint ukos76yivkv130d8fqdacvkkwi1
			unique
		constraint fk101002cw0p0dxxop7tl4uu60c
			references product_option_values
);

alter table product_option_value_descriptions owner to postgres;

create index prd_option_val_code_idx
	on product_option_values (product_option_val_code);

create table product_options
(
	id bigserial not null
		constraint product_options_pkey
			primary key,
	product_option_code varchar(255),
	product_option_sort_ord integer,
	product_option_type varchar(10),
	product_option_read boolean,
	merchant_id bigint not null
		constraint fkjwnrs1jste1sx6hiwdcjdph2i
			references merchant_stores,
	constraint uk19tgqepglu5wp4g0p4o7xcvqo
		unique (merchant_id, product_option_code)
);

alter table product_options owner to postgres;

create table product_option_descs
(
	id bigserial not null
		constraint product_option_descs_pkey
			primary key,
	description text,
	name varchar(120) not null,
	title varchar(100),
	product_option_comment text,
	product_option_id bigint not null
		constraint ukdwaf12t490y8e6jfescfpmnwb
			unique
		constraint fkjvv628xvv5gf6jrf0p2rmfxki
			references product_options
);

alter table product_option_descs owner to postgres;

create index prd_option_code_idx
	on product_options (product_option_code);

create table shipping_origins
(
	id bigserial not null
		constraint shipping_origins_pkey
			primary key,
	active boolean,
	street_address varchar(256),
	city varchar(100),
	postcode varchar(20),
	state varchar(100),
	country_id bigint
		constraint fk75975lek75a3ra6y8lm2s2mmm
			references countries,
	merchant_id bigint not null
		constraint fko57c8g5jdkddy0rreb4r9cgy
			references merchant_stores,
	zone_id bigint
		constraint fkb3si1b7rt4bmblolswfdg98pn
			references zones
);

alter table shipping_origins owner to postgres;

create table shipping_quotes
(
	id bigserial not null
		constraint shipping_quotes_pkey
			primary key,
	cart_id bigint,
	customer_id bigint,
	delivery_street_address varchar(256),
	delivery_city varchar(100),
	delivery_company varchar(100),
	delivery_first_name varchar(64),
	delivery_last_name varchar(64),
	delivery_postcode varchar(20),
	delivery_state varchar(100),
	delivery_telephone varchar(32),
	shipping_number_days integer,
	free_shipping boolean,
	quote_handling numeric(19,2),
	module varchar(255) not null,
	option_code varchar(255),
	option_delivery_date timestamp,
	option_name varchar(255),
	option_shipping_date timestamp,
	order_id bigint,
	quote_price numeric(19,2),
	quote_date timestamp,
	delivery_country_id bigint
		constraint fktbalc0uq1qgcoekdi3btydmhc
			references countries,
	delivery_zone_id bigint
		constraint fkrvrwrb1b9xfxxb8ofkcdymk4h
			references zones
);

alter table shipping_quotes owner to postgres;

create table shopping_carts
(
	id bigserial not null
		constraint shopping_carts_pkey
			primary key,
	customer_id bigint,
	shp_cart_code varchar(255) not null
		constraint uk_a8akc2oxpu38wy0c5bmp2do99
			unique,
	merchant_id bigint not null
		constraint fk37gcamxfh6s3u25lweguo9l51
			references merchant_stores
);

alter table shopping_carts owner to postgres;

create table shopping_cart_items
(
	id bigserial not null
		constraint shopping_cart_items_pkey
			primary key,
	product_id bigint not null,
	quantity integer,
	shp_cart_id bigint not null
		constraint fkfbnacrsbahkm1jmhhgies7x3m
			references shopping_carts
);

alter table shopping_cart_items owner to postgres;

create table shopping_cart_attr_items
(
	id bigserial not null
		constraint shopping_cart_attr_items_pkey
			primary key,
	product_attr_id bigint not null,
	shp_cart_item_id bigint not null
		constraint fkikhcx9wp1fecd607t3k912rlc
			references shopping_cart_items
);

alter table shopping_cart_attr_items owner to postgres;

create index shp_cart_code_idx
	on shopping_carts (shp_cart_code);

create index shp_cart_customer_idx
	on shopping_carts (customer_id);

create table sm_transactions
(
	id bigserial not null
		constraint sm_transactions_pkey
			primary key,
	amount numeric(19,2),
	details text,
	payment_type varchar(255),
	transaction_date timestamp,
	transaction_type varchar(255),
	order_id bigint
		constraint fk2o63am5tfh8bd4j86gp3xeh0x
			references orders
);

alter table sm_transactions owner to postgres;

create table tax_classes
(
	id bigserial not null
		constraint tax_classes_pkey
			primary key,
	tax_class_code varchar(10) not null,
	tax_class_title varchar(32) not null,
	merchant_id bigint
		constraint fkb8bc9sylp952px3b3c5cluc0t
			references merchant_stores,
	constraint ukgixm9on162twcmfriwcw6lw5p
		unique (merchant_id, tax_class_code)
);

alter table tax_classes owner to postgres;

create table products
(
	id bigserial not null
		constraint products_pkey
			primary key,
	available boolean,
	cond integer,
	date_available timestamp,
	preorder boolean,
	product_height numeric(19,2),
	product_free boolean,
	product_length numeric(19,2),
	quantity_ordered integer,
	review_avg numeric(19,2),
	review_count integer,
	product_ship boolean,
	product_virtual boolean,
	product_weight numeric(19,2),
	product_width numeric(19,2),
	ref_sku varchar(255),
	rental_duration integer,
	rental_period integer,
	rental_status integer,
	sku varchar(255),
	sort_order integer,
	manufacturer_id bigint
		constraint fkljnead8q1652k9q5p0fe0o1g2
			references manufacturers,
	merchant_id bigint not null
		constraint fk7tymiw9docppx90cx30ua68vb
			references merchant_stores,
	customer_id bigint
		constraint fk29w1glmsx19fyn0ts34ak8pc5
			references customers,
	tax_class_id bigint
		constraint fkcuthw01h72hkvro9a719yvh9h
			references tax_classes,
	product_type_id bigint
		constraint fkrv6og3b2qlahvka0bxn7btyqd
			references product_types,
	constraint uke1t3ytrbm02gp7bxiajo78q4l
		unique (merchant_id, sku)
);

alter table products owner to postgres;

create table product_attributes
(
	id bigserial not null
		constraint product_attributes_pkey
			primary key,
	product_attribute_default boolean,
	product_attribute_discounted boolean,
	product_attribute_for_disp boolean,
	product_attribute_required boolean,
	product_attribute_free boolean,
	product_attribute_price numeric(19,2),
	product_attribute_weight numeric(19,2),
	product_attribute_sort_ord integer,
	product_id bigint not null
		constraint fkcex46yvx4g18b2pn09p79h1mc
			references products,
	option_id bigint not null
		constraint fk1ic5jd8j62xxmnkrwmpn77qbh
			references product_options,
	option_value_id bigint not null
		constraint fk818luc9l11me1o7kchvp7iw33
			references product_option_values,
	constraint uk5ekq4whkeo74cxj57ed8snmrm
		unique (option_id, option_value_id, product_id)
);

alter table product_attributes owner to postgres;

create table product_availabilities
(
	id bigserial not null
		constraint product_availabilities_pkey
			primary key,
	date_available date,
	free_shipping boolean,
	quantity integer not null,
	quantity_ord_max integer,
	quantity_ord_min integer,
	status boolean,
	product_id bigint not null
		constraint fkfpdqtptmobkf4t0ek4m40np2w
			references products
);

alter table product_availabilities owner to postgres;

create table product_categories
(
	product_id bigint not null
		constraint fklda9rad6s180ha3dl1ncsp8n7
			references products,
	category_id bigint not null
		constraint fkd112rx0alycddsms029iifrih
			references categories,
	constraint product_categories_pkey
		primary key (product_id, category_id)
);

alter table product_categories owner to postgres;

create table product_descriptions
(
	id bigserial not null
		constraint product_descriptions_pkey
			primary key,
	description text,
	name varchar(120) not null,
	title varchar(100),
	meta_description varchar(255),
	meta_keywords varchar(255),
	meta_title varchar(255),
	download_lnk varchar(255),
	product_highlight varchar(255),
	sef_url varchar(255),
	product_id bigint not null
		constraint uk1j43k7c1isaen8j6sv33uakg8
			unique
		constraint fkr2nayvgycfj7grrhagiojxct6
			references products
);

alter table product_descriptions owner to postgres;

create table product_digitals
(
	id bigserial not null
		constraint product_digitals_pkey
			primary key,
	file_name varchar(255) not null,
	product_id bigint not null
		constraint fk7bqtxqeh14fx6wo2bypms2682
			references products,
	constraint ukot8138ieqk1denf0dy2pgmcb2
		unique (product_id, file_name)
);

alter table product_digitals owner to postgres;

create table product_images
(
	id bigserial not null
		constraint product_images_pkey
			primary key,
	default_image boolean,
	image_crop boolean,
	image_type integer,
	product_image varchar(255),
	product_image_url varchar(255),
	product_id bigint not null
		constraint fkqnq71xsohugpqwf3c9gxmsuy
			references products
);

alter table product_images owner to postgres;

create table product_image_descriptions
(
	id bigserial not null
		constraint product_image_descriptions_pkey
			primary key,
	description text,
	name varchar(120) not null,
	title varchar(100),
	alt_tag varchar(100),
	product_image_id bigint not null
		constraint ukfvotg3xc4ox3g54kh0r9kt9u8
			unique
		constraint fkj90oy742lt97way9xbarsffce
			references product_images
);

alter table product_image_descriptions owner to postgres;

create table product_prices
(
	id bigserial not null
		constraint product_prices_pkey
			primary key,
	default_price boolean,
	product_price_amount numeric(19,2) not null,
	product_price_special_amount numeric(19,2),
	product_price_special_end_date date,
	product_price_special_st_date date,
	product_price_type varchar(20),
	product_avail_id bigint not null
		constraint fkpkxowfi761tsn3ob6b1itijgr
			references product_availabilities
);

alter table product_prices owner to postgres;

create table product_price_descriptions
(
	id bigserial not null
		constraint product_price_descriptions_pkey
			primary key,
	description text,
	name varchar(120) not null,
	title varchar(100),
	product_price_id bigint not null
		constraint uki8x2ix3u7inbn8abnh4hdhapa
			unique
		constraint fkotfrrxncdlhav0o9y9lqv4nfg
			references product_prices
);

alter table product_price_descriptions owner to postgres;

create table product_relationships
(
	id bigserial not null
		constraint product_relationships_pkey
			primary key,
	active boolean,
	code varchar(255),
	product_id bigint
		constraint fkhnf83r8nmat84ekvexot9ndig
			references products,
	related_product_id bigint
		constraint fk60vl90badifsj3o8v14env0vr
			references products,
	merchant_id bigint not null
		constraint fkqdrhqvqchsl0kk2sab0ba5oyw
			references merchant_stores
);

alter table product_relationships owner to postgres;

create table product_reviews
(
	id bigserial not null
		constraint product_reviews_pkey
			primary key,
	review_date timestamp,
	reviews_rating double precision,
	reviews_read bigint,
	status integer,
	customers_id bigint
		constraint fkh9pq9ai2y98pikoxdyu1ypx4w
			references customers,
	product_id bigint
		constraint fk35kxxqe2g9r4mww80w9e3tnw9
			references products,
	constraint ukp9ywikfuj5yuxgo858bv5pa1m
		unique (customers_id, product_id)
);

alter table product_reviews owner to postgres;

create table product_review_descriptions
(
	id bigserial not null
		constraint product_review_descriptions_pkey
			primary key,
	description text,
	name varchar(120) not null,
	title varchar(100),
	product_review_id bigint
		constraint ukf6k67e40w5pbuh8yvnj32lare
			unique
		constraint fki2qfrbx0s002quplqwy038k8e
			references product_reviews
);

alter table product_review_descriptions owner to postgres;

create table tax_rates
(
	id bigserial not null
		constraint tax_rates_pkey
			primary key,
	tax_code varchar(255),
	piggyback boolean,
	store_state_prov varchar(100),
	tax_priority integer,
	tax_rate numeric(7,4) not null,
	country_id bigint not null
		constraint fk338qekvpg8b4oumcgjut6gccv
			references countries,
	merchant_id bigint not null
		constraint fk4jtf4v72upn0gc5d82vdhxxwr
			references merchant_stores,
	parent_id bigint
		constraint fk2uv0s1ccg8c7bivheyvq10n3w
			references tax_rates,
	tax_class_id bigint not null
		constraint fktb11wk0mrp6tem4ru7nfxovma
			references tax_classes,
	zone_id bigint
		constraint fklqa4vwfck9xkqrkbstisqyp5n
			references zones,
	constraint ukds8dggn3lhtq8m4toev60k6xo
		unique (tax_code, merchant_id)
);

alter table tax_rates owner to postgres;

create table tax_rate_descriptions
(
	id bigserial not null
		constraint tax_rate_descriptions_pkey
			primary key,
	description text,
	name varchar(120) not null,
	title varchar(100),
	tax_rate_id bigint
		constraint uk812txy3bl1mwasjpqh5hdxe9w
			unique
		constraint fkat2ytqdagvodnw9ukmtkxeee9
			references tax_rates
);

alter table tax_rate_descriptions owner to postgres;

create table zone_descriptions
(
	id bigserial not null
		constraint zone_descriptions_pkey
			primary key,
	description text,
	name varchar(120) not null,
	title varchar(100),
	zone_id bigint not null
		constraint ukjrpdd6p8hyiwduexfoysv1eah
			unique
		constraint fksnf56r76rgldnj7gssjwbsqk5
			references zones
);

alter table zone_descriptions owner to postgres;
