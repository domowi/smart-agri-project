PGDMP     %        	            x         
   smart_agri    10.4    12.2 K    ]           0    0    ENCODING    ENCODING     !   SET client_encoding = 'WIN1252';
                      false            ^           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            _           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            `           1262    16384 
   smart_agri    DATABASE     �   CREATE DATABASE smart_agri WITH TEMPLATE = template0 ENCODING = 'WIN1252' LC_COLLATE = 'English_Kenya.1252' LC_CTYPE = 'English_Kenya.1252';
    DROP DATABASE smart_agri;
             	   odhiambod    false            �            1259    40992    commodities    TABLE     �   CREATE TABLE public.commodities (
    id integer NOT NULL,
    variety_id integer NOT NULL,
    commodity character varying(255)[],
    code integer[],
    description text,
    date_created time with time zone,
    date_modified time with time zone
);
    DROP TABLE public.commodities;
       public            postgres    false            a           0    0    TABLE commodities    COMMENT     P   COMMENT ON TABLE public.commodities IS 'Table to hold commodities information';
          public          postgres    false    206            �            1259    32777    counties    TABLE       CREATE TABLE public.counties (
    id integer NOT NULL,
    county character varying(150) NOT NULL,
    code smallint NOT NULL,
    location point,
    area_sq_km real,
    date_created timestamp with time zone,
    date_modified timestamp with time zone
);
    DROP TABLE public.counties;
       public            postgres    false            b           0    0    TABLE counties    COMMENT     C   COMMENT ON TABLE public.counties IS 'Table to hold counties data';
          public          postgres    false    199            �            1259    32775    counties_id_seq    SEQUENCE     �   CREATE SEQUENCE public.counties_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.counties_id_seq;
       public          postgres    false    199            c           0    0    counties_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.counties_id_seq OWNED BY public.counties.id;
          public          postgres    false    198            �            1259    65556    hibernate_sequence    SEQUENCE     {   CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hibernate_sequence;
       public       
   smart_agri    false            �            1259    24616 	   inflation    TABLE     k  CREATE TABLE public.inflation (
    id integer NOT NULL,
    year smallint NOT NULL,
    month character varying(12) NOT NULL,
    annual_average_inflation double precision NOT NULL,
    twelve_month_inflation double precision NOT NULL,
    date_created timestamp with time zone DEFAULT now(),
    date_modified timestamp with time zone DEFAULT now() NOT NULL
);
    DROP TABLE public.inflation;
       public            postgres    false            d           0    0    TABLE inflation    COMMENT     E   COMMENT ON TABLE public.inflation IS 'Table to hold inflation data';
          public          postgres    false    197            �            1259    24614    inflation_id_seq    SEQUENCE     �   CREATE SEQUENCE public.inflation_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.inflation_id_seq;
       public          postgres    false    197            e           0    0    inflation_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.inflation_id_seq OWNED BY public.inflation.id;
          public          postgres    false    196            �            1259    57472    nafis    TABLE     r  CREATE TABLE public.nafis (
    id integer NOT NULL,
    county character varying(60),
    nafis_date character varying(60),
    variety character varying(60),
    commodity character varying(120),
    unit character varying(25),
    kg smallint,
    nafis_prices double precision,
    season character varying(10),
    avg_temp integer,
    max_temp integer,
    min_temp integer,
    precipitation_mm integer,
    annual_avg_inflation double precision,
    twelve_month_inflation double precision,
    household_sizes integer,
    total_household_demand_kg integer,
    datecreated timestamp with time zone DEFAULT now()
);
    DROP TABLE public.nafis;
       public            postgres    false            �            1259    57470    nafis_id_seq    SEQUENCE     �   CREATE SEQUENCE public.nafis_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.nafis_id_seq;
       public          postgres    false    209            f           0    0    nafis_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.nafis_id_seq OWNED BY public.nafis.id;
          public          postgres    false    208            �            1259    57481 	   nafis_raw    TABLE     �  CREATE TABLE public.nafis_raw (
    id integer NOT NULL,
    rowno integer,
    variety character varying(60),
    commodity character varying(120),
    unit character varying(25),
    kg character varying(60),
    code smallint,
    nairobi character varying(25),
    mombasa character varying(25),
    kisumu character varying(25),
    nakuru character varying(25),
    kisii character varying(25),
    eldoret character varying(25),
    busia character varying(25),
    kitale character varying(25),
    isiolo character varying(25),
    loitktk character varying(25),
    malindi character varying(25),
    kakamega character varying(25),
    kitui character varying(25),
    wajir character varying(25),
    imenti character varying(25),
    machakos character varying(25),
    chwele character varying(25),
    taveta character varying(25),
    karatina character varying(25),
    nyahururu character varying(25),
    garissa character varying(25),
    average double precision,
    max integer,
    min integer,
    price_date character varying(255),
    filename character varying(225),
    dateofupload character varying(225),
    datecreated timestamp with time zone DEFAULT now()
);
    DROP TABLE public.nafis_raw;
       public            postgres    false            �            1259    57344    nafis_raw_2    TABLE     �  CREATE TABLE public.nafis_raw_2 (
    id integer,
    variety character varying(60),
    commodity character varying(60),
    unit character varying(25),
    kg character varying(60),
    code smallint,
    nairobi integer,
    mombasa integer,
    kisumu integer,
    nakuru integer,
    kisii integer,
    eldoret integer,
    busia integer,
    kitale integer,
    isiolo integer,
    loitktk integer,
    average double precision,
    max integer,
    min integer,
    wajir integer,
    imenti integer
);
    DROP TABLE public.nafis_raw_2;
       public            postgres    false            �            1259    57479    nafis_raw_id_seq    SEQUENCE     �   CREATE SEQUENCE public.nafis_raw_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.nafis_raw_id_seq;
       public          postgres    false    211            g           0    0    nafis_raw_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.nafis_raw_id_seq OWNED BY public.nafis_raw.id;
          public          postgres    false    210            �            1259    73767    prices    TABLE     �   CREATE TABLE public.prices (
    id integer NOT NULL,
    county character varying(60),
    nafis_date character varying(60),
    commodity character varying(120),
    nafis_prices double precision
)
PARTITION BY LIST (county);
    DROP TABLE public.prices;
       public            postgres    false            �            1259    73776    prices_kisumu    TABLE     /  CREATE TABLE public.prices_kisumu (
    id integer NOT NULL,
    county character varying(60),
    nafis_date character varying(60),
    commodity character varying(120),
    nafis_prices double precision
);
ALTER TABLE ONLY public.prices ATTACH PARTITION public.prices_kisumu FOR VALUES IN ('Kisumu');
 !   DROP TABLE public.prices_kisumu;
       public            postgres    false    213            �            1259    73773    prices_mombasa    TABLE     2  CREATE TABLE public.prices_mombasa (
    id integer NOT NULL,
    county character varying(60),
    nafis_date character varying(60),
    commodity character varying(120),
    nafis_prices double precision
);
ALTER TABLE ONLY public.prices ATTACH PARTITION public.prices_mombasa FOR VALUES IN ('Mombasa');
 "   DROP TABLE public.prices_mombasa;
       public            postgres    false    213            �            1259    73770    prices_nairobi    TABLE     2  CREATE TABLE public.prices_nairobi (
    id integer NOT NULL,
    county character varying(60),
    nafis_date character varying(60),
    commodity character varying(120),
    nafis_prices double precision
);
ALTER TABLE ONLY public.prices ATTACH PARTITION public.prices_nairobi FOR VALUES IN ('Nairobi');
 "   DROP TABLE public.prices_nairobi;
       public            postgres    false    213            �            1259    40960    seasons    TABLE     �   CREATE TABLE public.seasons (
    id integer NOT NULL,
    season "char"[],
    description text,
    date_created timestamp with time zone,
    date_modified timestamp with time zone
);
    DROP TABLE public.seasons;
       public            postgres    false            �            1259    32787    towns    TABLE     �   CREATE TABLE public.towns (
    id integer NOT NULL,
    county_id integer NOT NULL,
    town character varying(225) NOT NULL,
    area_sq_km real,
    date_created timestamp with time zone,
    date_modified timestamp with time zone
);
    DROP TABLE public.towns;
       public            postgres    false            h           0    0    TABLE towns    COMMENT     E   COMMENT ON TABLE public.towns IS 'Table to hold town - cities data';
          public          postgres    false    201            �            1259    32785    towns_id_seq    SEQUENCE     �   CREATE SEQUENCE public.towns_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.towns_id_seq;
       public          postgres    false    201            i           0    0    towns_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.towns_id_seq OWNED BY public.towns.id;
          public          postgres    false    200            �            1259    40984 	   varieties    TABLE     �   CREATE TABLE public.varieties (
    id integer NOT NULL,
    variety character varying(255)[],
    description text,
    date_created time with time zone,
    date_modified time with time zone
);
    DROP TABLE public.varieties;
       public            postgres    false            j           0    0    TABLE varieties    COMMENT     Z   COMMENT ON TABLE public.varieties IS 'Table to hold agricultural varieties - categories';
          public          postgres    false    205            �            1259    32825    weather    TABLE     �  CREATE TABLE public.weather (
    id integer NOT NULL,
    town_id integer NOT NULL,
    date_of_weather_forecast date NOT NULL,
    average_temperature real NOT NULL,
    maximum_temperature real NOT NULL,
    minimum_temperature real NOT NULL,
    precipitation_mm real NOT NULL,
    average_humidity real,
    maximum_humidity real,
    minimum_humidity real,
    wind_speed real,
    date_created timestamp with time zone,
    date_modified timestamp with time zone
);
    DROP TABLE public.weather;
       public            postgres    false            k           0    0    TABLE weather    COMMENT     B   COMMENT ON TABLE public.weather IS 'Table to store weather data';
          public          postgres    false    203            �            1259    32823    weather_id_seq    SEQUENCE     �   CREATE SEQUENCE public.weather_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.weather_id_seq;
       public          postgres    false    203            l           0    0    weather_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.weather_id_seq OWNED BY public.weather.id;
          public          postgres    false    202            �
           2604    32780    counties id    DEFAULT     j   ALTER TABLE ONLY public.counties ALTER COLUMN id SET DEFAULT nextval('public.counties_id_seq'::regclass);
 :   ALTER TABLE public.counties ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    198    199    199            �
           2604    24619    inflation id    DEFAULT     l   ALTER TABLE ONLY public.inflation ALTER COLUMN id SET DEFAULT nextval('public.inflation_id_seq'::regclass);
 ;   ALTER TABLE public.inflation ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    197    196    197            �
           2604    57475    nafis id    DEFAULT     d   ALTER TABLE ONLY public.nafis ALTER COLUMN id SET DEFAULT nextval('public.nafis_id_seq'::regclass);
 7   ALTER TABLE public.nafis ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    208    209    209            �
           2604    57484    nafis_raw id    DEFAULT     l   ALTER TABLE ONLY public.nafis_raw ALTER COLUMN id SET DEFAULT nextval('public.nafis_raw_id_seq'::regclass);
 ;   ALTER TABLE public.nafis_raw ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    211    210    211            �
           2604    32790    towns id    DEFAULT     d   ALTER TABLE ONLY public.towns ALTER COLUMN id SET DEFAULT nextval('public.towns_id_seq'::regclass);
 7   ALTER TABLE public.towns ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    201    200    201            �
           2604    32828 
   weather id    DEFAULT     h   ALTER TABLE ONLY public.weather ALTER COLUMN id SET DEFAULT nextval('public.weather_id_seq'::regclass);
 9   ALTER TABLE public.weather ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    202    203    203            Q          0    40992    commodities 
   TABLE DATA           p   COPY public.commodities (id, variety_id, commodity, code, description, date_created, date_modified) FROM stdin;
    public          postgres    false    206   �^       J          0    32777    counties 
   TABLE DATA           g   COPY public.counties (id, county, code, location, area_sq_km, date_created, date_modified) FROM stdin;
    public          postgres    false    199   �^       H          0    24616 	   inflation 
   TABLE DATA           �   COPY public.inflation (id, year, month, annual_average_inflation, twelve_month_inflation, date_created, date_modified) FROM stdin;
    public          postgres    false    197   b       T          0    57472    nafis 
   TABLE DATA             COPY public.nafis (id, county, nafis_date, variety, commodity, unit, kg, nafis_prices, season, avg_temp, max_temp, min_temp, precipitation_mm, annual_avg_inflation, twelve_month_inflation, household_sizes, total_household_demand_kg, datecreated) FROM stdin;
    public          postgres    false    209   �k       V          0    57481 	   nafis_raw 
   TABLE DATA           I  COPY public.nafis_raw (id, rowno, variety, commodity, unit, kg, code, nairobi, mombasa, kisumu, nakuru, kisii, eldoret, busia, kitale, isiolo, loitktk, malindi, kakamega, kitui, wajir, imenti, machakos, chwele, taveta, karatina, nyahururu, garissa, average, max, min, price_date, filename, dateofupload, datecreated) FROM stdin;
    public          postgres    false    211   y�       R          0    57344    nafis_raw_2 
   TABLE DATA           �   COPY public.nafis_raw_2 (id, variety, commodity, unit, kg, code, nairobi, mombasa, kisumu, nakuru, kisii, eldoret, busia, kitale, isiolo, loitktk, average, max, min, wajir, imenti) FROM stdin;
    public          postgres    false    207   �&      Z          0    73776    prices_kisumu 
   TABLE DATA           X   COPY public.prices_kisumu (id, county, nafis_date, commodity, nafis_prices) FROM stdin;
    public          postgres    false    216   u0      Y          0    73773    prices_mombasa 
   TABLE DATA           Y   COPY public.prices_mombasa (id, county, nafis_date, commodity, nafis_prices) FROM stdin;
    public          postgres    false    215   2      X          0    73770    prices_nairobi 
   TABLE DATA           Y   COPY public.prices_nairobi (id, county, nafis_date, commodity, nafis_prices) FROM stdin;
    public          postgres    false    214   �5      O          0    40960    seasons 
   TABLE DATA           W   COPY public.seasons (id, season, description, date_created, date_modified) FROM stdin;
    public          postgres    false    204   �6      L          0    32787    towns 
   TABLE DATA           ]   COPY public.towns (id, county_id, town, area_sq_km, date_created, date_modified) FROM stdin;
    public          postgres    false    201   �6      P          0    40984 	   varieties 
   TABLE DATA           Z   COPY public.varieties (id, variety, description, date_created, date_modified) FROM stdin;
    public          postgres    false    205   �8      N          0    32825    weather 
   TABLE DATA           �   COPY public.weather (id, town_id, date_of_weather_forecast, average_temperature, maximum_temperature, minimum_temperature, precipitation_mm, average_humidity, maximum_humidity, minimum_humidity, wind_speed, date_created, date_modified) FROM stdin;
    public          postgres    false    203   �8      m           0    0    counties_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.counties_id_seq', 47, true);
          public          postgres    false    198            n           0    0    hibernate_sequence    SEQUENCE SET     A   SELECT pg_catalog.setval('public.hibernate_sequence', 1, false);
          public       
   smart_agri    false    212            o           0    0    inflation_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.inflation_id_seq', 179, true);
          public          postgres    false    196            p           0    0    nafis_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.nafis_id_seq', 989, true);
          public          postgres    false    208            q           0    0    nafis_raw_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.nafis_raw_id_seq', 1293, true);
          public          postgres    false    210            r           0    0    towns_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.towns_id_seq', 47, true);
          public          postgres    false    200            s           0    0    weather_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.weather_id_seq', 1, false);
          public          postgres    false    202            �
           2606    40999    commodities commodities_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.commodities
    ADD CONSTRAINT commodities_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.commodities DROP CONSTRAINT commodities_pkey;
       public            postgres    false    206            �
           2606    32782    counties counties_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.counties
    ADD CONSTRAINT counties_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.counties DROP CONSTRAINT counties_pkey;
       public            postgres    false    199            �
           2606    24621    inflation inflation_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.inflation
    ADD CONSTRAINT inflation_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.inflation DROP CONSTRAINT inflation_pkey;
       public            postgres    false    197            �
           2606    57478    nafis nafis_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.nafis
    ADD CONSTRAINT nafis_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.nafis DROP CONSTRAINT nafis_pkey;
       public            postgres    false    209            �
           2606    57487    nafis_raw nafis_raw_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.nafis_raw
    ADD CONSTRAINT nafis_raw_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.nafis_raw DROP CONSTRAINT nafis_raw_pkey;
       public            postgres    false    211            �
           2606    40967    seasons seasons_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.seasons
    ADD CONSTRAINT seasons_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.seasons DROP CONSTRAINT seasons_pkey;
       public            postgres    false    204            �
           2606    40991    varieties varieties_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.varieties
    ADD CONSTRAINT varieties_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.varieties DROP CONSTRAINT varieties_pkey;
       public            postgres    false    205            �
           1259    73781    prices_kisumu_nafis_date_idx    INDEX     \   CREATE INDEX prices_kisumu_nafis_date_idx ON public.prices_kisumu USING btree (nafis_date);
 0   DROP INDEX public.prices_kisumu_nafis_date_idx;
       public            postgres    false    216            �
           1259    73780    prices_mombasa_nafis_date_idx    INDEX     ^   CREATE INDEX prices_mombasa_nafis_date_idx ON public.prices_mombasa USING btree (nafis_date);
 1   DROP INDEX public.prices_mombasa_nafis_date_idx;
       public            postgres    false    215            �
           1259    73779    prices_nairobi_nafis_date_idx    INDEX     ^   CREATE INDEX prices_nairobi_nafis_date_idx ON public.prices_nairobi USING btree (nafis_date);
 1   DROP INDEX public.prices_nairobi_nafis_date_idx;
       public            postgres    false    214            �
           2606    41000 $   commodities fk_commodities_varieties    FK CONSTRAINT     �   ALTER TABLE ONLY public.commodities
    ADD CONSTRAINT fk_commodities_varieties FOREIGN KEY (variety_id) REFERENCES public.varieties(id);
 N   ALTER TABLE ONLY public.commodities DROP CONSTRAINT fk_commodities_varieties;
       public          postgres    false    205    206    2754            �
           2606    32791    towns fk_towns_counties    FK CONSTRAINT     {   ALTER TABLE ONLY public.towns
    ADD CONSTRAINT fk_towns_counties FOREIGN KEY (county_id) REFERENCES public.counties(id);
 A   ALTER TABLE ONLY public.towns DROP CONSTRAINT fk_towns_counties;
       public          postgres    false    201    2750    199            Q      x������ � �      J   �  x�U��n9E���nVn�,>��d��0H<��7t�#1m���ʇ�{sIJ2���ާnQ�zܿ�9���)�:�<o�=���=���+':�?��k�/�R)m� �>�5=�C\}L����G6�L煸d0�W�/T�Y\�Pg�Ex:�_O�{�dK'<�5��S�Ѻ�_�qJt2�KO��4Q)h�ע�Bڳ5�:��S�P7-طh)�0��%	�s�6(��[
)��9��#�
�0�N�puP��xJ|`�'��X��vq�C�ߤ�.�,,������t-`�0�p�l� ��H�榾�_vqg�]�`�M�a��Q(��8xڜ *N$RP��T�6��OHQQ�����������R���m�J�z��a�\*GVE)�DH1��A�:�y��P.� �!8s�dE{�Ci�������0��̪��TӉ�O�
6�*P!}sP%���7?ǄB��bC�K9�'��z����*`o�����?���,�=Z�l&k�*���%����z��#�
[l��\�đ�!��~���6T�^V�
�<3�4�c�q�z�.(ٞkzčů�@�ߏ��3bH�����Y�����ø��\O<`i��;�⾇|�U@�7�`O��]���Yf�_zX�h��P	ۮL�2�����7Ւ>�x���S�`s�:�s^��*N� �LPy�O��^�o7�5��v����{�q�^>�9�\"$�[F���'�d�P�����%�H��
�~yF������
�d�      H   t	  x���M�]���׿�� ��ou �@�E��8Ơ-�&��.�_J�ΑădA"�Wwl=�����q/g��������~�������q/o��>������d����_�B��e�o�����͗}��3�v��(>����ï8�w�����Ku��D�8i?�!�1O���:'��w}��c3g*�P��ce~~��8v�ÚdbQt����Ꜽ�O�F\�ݠ�G>(zX������/�m�xxz0�7d�!ax��'1�]������۷O$�����d� ��˱xQ�W �O��]]#������?2uW/sH1��c{�"��1��x|}�MW&��;g+W��D�J8��ǃ��+�9;��fz:����{��4�L��۬���"���42�h �8�O�!S��ͱUc�wI�u0�Z�!���Eb\2X�%��#2r�`1�����G1���]"E�bD#��~d�sZ�-��]�q�nԶ��D6��N�":;��U�7�pM3n��H0g�%���x]3�|����i�b�ݣ���Mbr�b2a?�3���G��-S#�p9�L�j����t+&"+C�>�ЇyM<d!!��"�<��f�D�Ө6��>�ؖ C1Lxp �q�O�yH�pn�P1�u�;�S�h����a�Έ�uk !NcF�xLU�A�88�X���yQ���G��q�c>g�J�Ԃά��#�p1,��NY	����:-4a5	2ZB���w�ғ���@�U]�TM��(�@2���=�@�&���"�	��*�k�Cb$	$+�=�x-����jXh�fy)�i)&�͇} �1Y�!�"��E1�r�r-u-	�b>�Dvټ& 䢘LFy�"�#�T��|kEDO�[1��s����F
'6s�g�u��,]����`_��"\�8TS\�=�)���&�e��3��<���X���,niq����:�#5eS�|症�,j�zĹ����3i����z�8���%�V̇b^�EF��R�ȗ�_u&!�bƧ��j:"b$�z����vS����r�޴4_U�r&¸�yȺUc�\&E�9k�W�"\%\E:��.E/(v�O����J�����g��yd"O3�+C=��v#t3�2�c�b1G~���T�@���n���W�E���,BFB^	���(��PI4��ApgY�#3oj�S���Y�9T���Tbt]�h\;�Xz#!T;	(�H�[��%l�?�pM
��+��in,QFh�i�+ʪ�&4��V#>�@�#(oå~m�|���T-���\�rVd�>�pK�voVL�mERX��<4��Q��R�r��P��N}�q��Z	T42�4�Шڽf��R `d���n�� }_��A��;X���}��M�X���d���`㴈Y+gEw����`�F_:Y��K�2���S�<��aX��
2d�-{+��p>jގ �Eص��nU���.}�p�=z�b��Dj��]�zi��s�.��g��A��0O���RWo��P�����&�u���:H�PHV��\�e�4pT�X�2��We�<�kz�p��0Y<�CV��Ma�m�"sg�i�|�A��z[)�ے�<^9I���\s\'}���G]CA�}
��Ԯof02�t�%���L�7c�k��k
v<�i�r+S����J��e��]����RK�d()�g�'qzè�w{�3�!��_�=�E��m}�s��^����&:Ex6�����5���^	R�)@������	8qe;x��z���[�V��-��G-M�	���,m��
���e��[��� �W�{=���e��q���d���Y��⃪���@%�����ٲ&��)Y9��}�A͊��N[�Vjv��R����i��&p��4O"�,\��ɠ�D��!�˻3���-�л<m^�ޱ[n��b����5f�;I�U
z���K
�Z�qMUS���i��o�I�����l^�s+[M�6���6����L�x����y����	��t�˚��Hc���i��f��r�SA��{_��W����8��BߏCV��g���2�������ר2dב���C�	{�'cfb.���~����~�ٴ��T޷��T���\�;x�{hv%Bo����=L�Z����nO�f�����?�xn����C?��c���i磔��ڪ���x�t���EE�G2`��5-e�s{�ɪ�2���KK+o���.eə�Z�^�*#��ĵ�X��t�w�W(l�
��x��1{ߧ�ky%ś�zM�?m<�f�Sx/�8�܍2異�#�e���f�a�:ʋ�� 4Ҡ��X��e�������'���&�bW�ӊ^C���n��.":���Һj��*�^����(�4>|�?Z�6      T      x�͝ۮ%7n����"�����0��
0�y�ܴ�A`Ď{��~$�A�՛d��vM:��vM�~Q�$�>�����~�񧇱6�������_��O�������<��������o����a���o��p���Æ�����L��/6��]n?eb�[M��$��W�b|��R���q�O������/��ŗ��s�_���M('�ʏ���'��(A�	dP^o�ℊ2(��N	*M�$�����	U�s�*A�	UoUw(kdPIo�ڿx�����Loc�S	������n���c5U����С��n�
�I5u��%Tm���,�
��0��
�=(~�S��}��Nmwmw�Tnj��h�S����v'���JG��v'�v����$ڮ<�S۝D�ݚ3$���N��n�{���N��NUE��v'�v7rQ5���N�����즶{���y��W~j��h�W�W�"+��T���o�|�yp�-Z1����r�1����%�`�55t�\�iX1�P(,l�Ȱ�6����~�	,�haGF����=q��vedXIo�/�Oa9���"1�QĊ#�W�[� �H--,�<#V y��X�F2����k$�R���m$�R�[�F��VB��m�2r�N`�,>� �H<���U�w$��:X� �H<��U�,r�N�J��N`�L��t�/1d �f�G� �����J�B�͔��C�͔/�D�˚+r�dXEm�'�l�X^96m�!^|B�z�����������/gS)>�L� �FR������>}!#�\9����ڢ��T
�0W��������3 ��g}\#��0�k�L}�H25� �A����t�zSZ���x
�)r�O爏�n �A�|h>	&�mh"u	y&g"��	љm�&
�$g"�	H4M[WJY3�E�|5��P�6G(e��	9�Ot$�l6JY3�>.��)ļM��5#����|����:Yy��g��ܕd��5#G��&��׮ ��f�a���)���+���g��'��T��P�Z�Oq�ZS[j��#�Rւ���A�JY�"�$Fߟ���yu�#���>�����j���R�Z��p���v3֯�V)e-�a���ڲΑJ^EF���q�m�b��� �∴��TJY�$ ~|��3B��JYr
� D}۟��R֊�yD>	!m�H���Ne�2e݆�3�Z�*�R�6����/?~����MO?�߼	�Eߖ��-�O(���FT���\\ԅ�Jro���F*M(ɵ�%R^	*O(ɭ���KU&������iA�	%���E�{Oe��T�;���g�ݩDo�6��C5E]��h�V�{�B��ꢷFUա��.zk�Qqo3�����mTY�j
���F�4ۧ���mTN�jJ��і.(QMm�5ڨt��Nm�5ҍ���.zk������F�렝�.zk��J#8�]��h�lZ����v�[��w~]�t�;�]��HwɱS�E��6*��p��豑�c���m{y����&Xn����&�J���.zl,��!�G��B�+����u�D2�}�Ǌ�&G�(E+!��� 2o�	�2��N�VAf�k&4�cUdɰ춳��d���|��"��6XYF'���#��D���9�X�F'����kt�AL�6��n��|��D� ��6s�"��.X� �H�U���"�H�e��2��XZ��{t�A�>�VD��Kk2�n����`� �f+�����2��"�.*r���96��E(Z�ny�|/ Sr�/و<!Ȝ�� W�+#�|9�uA��WFy-_	�ܕ/I�O�J�|%�L�:rrI΀|����"g@�{�W�����}|�dEn�W��o@�:G�{%^H�,!�{��d�b���E ȷ8��"����Aބ��WB�F���� ���EJ���p�"!��c��+�r��g��tHA�����HA^�MA����\�������F;�E�V��?���/���3��.�l]ۻ�����ZTܡ$W���v(��E�H�Jrq��zPe��\[\�"�����P�[�c�"Ž(��f��\Z��jw(ɝ�%RQ��P�+�c#�&	�oP��F�P���^վ��A�.zh�@e%I�����m���]�Eό6(�H�.ze��S���mP*�rfWt��6|�}�K�B�]�EO���vE�0jP�/3JP���-�RZ���]���A%5EwfWt����6E�+��u�2�ԠvE=.jP^qN�.zZ�A)��袗E������.zX�EJ	jWtѻ�*)��]��h�ҙ�vWtѫ"ݯ��.yTԽ�=R�+*B�� �řN�IŢr�d��ol�CR%hɨ���*C/HL%AU�fЉԡ����3��QE���,�nC�!$��JT:B'�t�U���m�"�nC��)$�Jzʐ�+$�Қ��B'�A'V�Bw��C2���U�SY�݆�AkH<�3�E���CoH<���7�4�N��)��*Bw�D�t���n3��C�oP)��D'b��:Dwѫ`�Et�zD'TT'V�D*�+]"1��2� m��PAOFrK��[B.{���:�n�QЅr��Y|�����.�x��8��"��u�[9Ñ���Mr�}�w[h9�"�H�K�����^3r@���wo�
9�+"�n~@D>O�e��8����:�̏��ݍ3�r�w��3��E	:g��E�~���]��r!t)���*�K���U�:g�q����_��B�+�����!|!G����K�>���Ǖ���3��r��\�����x�z*t�����}��:H���รb��e��2}�<��I&��{�0�s9WJ�ŷ|���5��h6����ڟ"��,�f�2�.����މ����7"�}˽�,j�F�����n����v�D���_�o��,〺+DN���ڲ�^2�2�L���C�4��562S|�w����E�E��gYz[�"Գ[�%�ęA��F���d������ide��O.�j�W�~&�[��ŒK5��RXrѿ���
�4oV�X2��{�@��~���o�h�/������0�.0W�;�$��U��P3<���!ZֆLmYy�
[��s˵R�*5��Mi!WDjq.���;qe$b4�WAv�xU$a4�}W���F\@�˝�P�uW|�~#.Ԉ��=��+��>\	��W��o����,?���Wev�
o�=vF�s�jl�m��p?��f�����&��K�%�*ځ�	��U�
͢Ă�߆�}�\{ڔ{��PFv+4���(2��G�����x�e׶mx�� h ��@9��f�b4��)H� Q	�z���8���@,Ư�#Ρ]����Ѷ�#�>�A��Z�dUIh�� m��p�Neu��F+"SY�Q��7h�u�����KL��ml��s͌�16b�fFK�{z.���C@0�VcX|8�L�����Z�`�]`@�ݭ�2�����XF���a��F`���X0hH��w��;/�V`������~��M������Ç���],תz߂���Xn�O��u����j�tuCBiύ�2�.h��.��qF����}GX|S�"��r#0|�y�9�'�`�]`�o1p����.0|�y��ľ���?��Hkǂ~Kɍ�\�M��l�m;�]YoS��QL�)�`@�l�&l?�+h_� ۼ
?��0J`-Ib����lS�F�/�:��LX�e`��NZ��e`Us(=ھ���X@���k(#ڌ��?�K(�v$��x�}��Ʊ�+ޗԄ�~?Nb�-ћ�����n��˔���W�?͵�D�������ˏ�?�N�i��	��G�s���y_��崸^\ngs�{J�+;�J�Ʊ,U�T�<2�\oǀ|1�+�q�Z\ �g}�s��7*\	��\^k~a��"�������5n�?��նH־@�Lz��d�B=��>�����*�aɧ�Bܓ��S*<�X��/�:�Y$4X�O	�Ws(o����F�4��{Vǚc	�1�9���    �mͯt�@�o`m��� E-b	mA`a�*�&?��X`�}а6\t��a���~��~�A֫:�=k�C4��]l��� &Of�ᛪv�.t�*Z.i2��zE���GJb�GS��\���w>��^I�$�NL��B��#J�m��e5��<(:f9�Q1/*�E�U���֣YB��d4�y��F���(�,��6���O�Ƅ�C/ٯ*�H�a����/Y$`��q+�'�n?��/��ۏ��!N�e`�ש��,��E50������*���R��	�?q�vX;b��O���u���~I
>%�����U���k�%PO�x��sl0~�DV��>�xmɢR���˸�TQ�<n���e�8�qY��ʸ��[�ˡ�U6�5���/�ʊ㈍~��F4����_��㨥_�)���*�����~�r9���c�t�馑��{/�	E]��`���|b�_��l>x�꣸�[T�N�Y�)�'�j`�$�7���G)�`(�~����-�ciI�u���`(aҪ�оH�:���C�Y�|k�����r�M�=1�ˆM<�헅�*��ե�^b��SXۻ��]H��%��S/�B?x"ɀ�{Y؝�8�%y52��4�3������ �5lh2��$I�,��M6ϸ��V�E��ь�,l��A�P+�gn���E�Q+$���fAB+'jd�fqe���֤1/��9��yj�h}t6�?���T�DB�/x�- �@խ?���P�����tnh�d:.�����X���ʣ4�T�g�<��;Ñ��a�9%8t�_�W���6����6Tؒ���}U`C�-�lI�c(�D��{>�ŨZ��6<iH��Ë��[�6�?����Rl���f�0M�g���s��a���-h�Y��2؞�ۈ[QbC%�N�e�1EU��l�U`C{�|K��XX��qAMjl�۩�T�Uh;5�AMC
��n�
�^�wj�㦦!	�v;�!끺p���犼��F���~g�>Յm�J̦6�X�>�B���++����/B��B1б�;w
��y%]7@���e���l�^�l��S�i���˝�\�,��Q��i����.���H0p��s� ۯ�k�������:Z%0���P�5Mp:`����Fl�ʩf�H>�js�ߍҡ̊_%x���g��4�����H"�7�.;2��,ɫ�%�ɇ2i�x��X:�_���z���M���ͱ
��4X��>��J��(+�������orȥ<����/!X��pw�<�灅��?��э�����e�v�������6����������4�_�]���I��y��4�pefY�v� �(��`ƿ?_� +(�ϱ�V�.[:��U\�Y1����2��gcEWf���,����=�t��(��1���j����e�]xivQF9�2Ҟ�hc�170;ޠ�
C����)%gL!���-��)[Y(��,(�z�[u�˹"|��\a����{\ɸ�VS��<Aʕ��3
\ȵ?/Wu���NL0���Y4�RF�#�4�J�d`N��(�q��ĪWAi��K-^%;2��U~���b���r��}��t+�٫���9�~��T
���W��%�~�2dc#��D�bw��EF֛Y�2 �FL��
�
d@�Y���փv��J!�H-�py��l$V+h��)g>����6zd�y�y2��!٘hN�"�B����G��=<�=�E����uQ�,hjb��#$.�����#�}?�A�P���J7��G�?��m)&�V� 9�m쵐�w1>J�=����G�o��`��W+(����1�wu���o����߿���m(�{��p�R��������9ż�\��'�+�Tt�.�+/\���������O�Q�O�UV.�MHlñq�:�l-�ȏ�"��p�͛zq�����$��5ޣ
��A��E�|عX=Oм�y_�]�2=��_�������?�����=\�6|+�ϑĪ`֗��3�������2�}���U&����XT�}�XS�Y�N�V�J+L�gu;q�ZXS�Y�NP�¦\�cM���:����yN�g^���XS�y�NR|!��cM�g5:q�=S�<Y��<���P8U�WL}��}�PNqa�g�9�:ߥ��Cf0,���}�6�
\X�i��*���+�$��n#��<�,�R�x�^j�rH�d�Ks=�/��.�7�#��Y�M�/���nC�5rU%. �������G��6��n }��3=�ũ���&��������`-�$X\=�rZ�ȗ��!�Y��X2h������a���E���l�L��rXc!�xʩ3��}V[��1~+!X@�"FW�.H*`-��i	l����i"fֈ%�����Q��&d~N����Ώ�2z�L��V���*`�4p��QV��8�q�����U�p�/�R��.��Rk�g���v&�M\@�Y�L`�F,�Å-|�(z�\�[���>�V��(��RjA��-s����?Z�������z:O��bϙ[�f���A	��j���/�X��w	6�I��?C�D��{�:l��V@k�t��"J�OKg����׽�ZXS��K�3��{��έ��iA��ũ�߸��_��8�U���`,~!��#hDP��N��ǡ��qڎQ̣�Z6��**p������v?$��H�7��q������$R	�-���v%��4��I_�$�����{�����~ﲁ�d=�K7�`�n%l����_�xcS|%0���^%�-��!�h�A��3��Ţ�p$�9�,	��2V9�Q�,��Uf��2�E� {��g���0M����m4��94��2uc;���̀�����ɬAӟ&�l�rד�g�(qx4���ӕ�����CΤ뛬���Vh�& B4t���j�6���b�qU�"T[1[�S��V֫g�m��W-C��QM=�@G�j�����1�CSo�*�)��a_�z4`�z��$�6��93[��y6�6��Z�����,�nG4~�!��'�"lN��O��o@#ZF���ZEo���Q%G�h����ЊR�s���a�OsY�7��� )�N��Fҝ�FkK���&����>]�� �guA$�J��u�3���]z��Su�������K*��<�'�� ��IrU���tf��~z�¯P/��0ɺW���Ks�U(��o ���
�����)r�{:��_OOm�\j��wuh���y۲��'.~!~m+�R�_	�B.��V5��2��x7����������z��O����B���u=xm�k�P����v9����my�Ņ���gZ�e\A1^�����1���+�u��:�~W����b��]�����㛾Ǌ����OmY����t<���a����j�0�&������J^��l9�����Z�[��!�X@�9���.mdѾ8���m~��=]�:�h٦���ܶB���m�@.<U�2Z�e�h��ƂmFi��`U�f��ij|\�*��dM���ֲ����@���:�
_ֲ����h��G�"]�w�p�A
��o�����k�e/kyE����(�a���P9��T ZϪ���KLJ`��g�����
XA��p(�b�*Zi0��ꁁW�����M��lYrq,��t�Uu�y�Ẹ�["��_e��d�mwX��d�`��t��xԽ	�.��S\����;l���2Z��x�굫V�p��_���_�����ζ����-O����7�Re�S�;���^�Ԫ-,5�Z\7,V�yP��׷^X*`��Wk��.z��~ņ�X�S���Z�s)`��3���Ԩ�NřZ��Røa�W�X�3s��cq� ����7,V�y�aGW���T��*�jbph��mb�ږ�v*�{�QTH�i*(򣝎U��C�}��z�e��\���/�` '��TS�Y�D5�տ Ru�h���*��PD���Bk
��eGK�eZ�SM}�t/�x�iTQ�j�w^� ��F+Q����]�beG �  %~*$���SZ+���ڧU;V����-RwV������wAG����>�x�?�x%,���`G�	��`V*�ZҬ���lɢ�K�V��%���aY����Zl�_d�Đq��q�
��yV���1��u�Sw*;WjS��rӪa��F���"��뢄k.�*\Z[�k[�2��*py�`����[����|�խ�����(�pEh$	Z��ELz����$�u���(� ��H��@�l�a,�{��v��u*(���:R�;O�?��ܺ��?�hX�U&���y�㬐_�^���y>�Z��$*X(��/�Y�
J�O`Y,����b�Ʉ��zV��',�A�J�jP��5��yqAf�V�K7�RǪ��X��4V��k��eT98���S�B"OS�;�Ky�뱦ȋgֺ$jPy��
rLyР
0A�Q�EK5��?/�����*>I��G��"-�UR�U���������O�&���.���I�jwqg�^�Qi�G�g����H�3���K��`�#
�C����~�V��C~�qpGJ+¼��y����+�Z0�p�Q�B��ADS^/ZnwdXq��U��Q#�� ��+��Ȱ�^��y^K�7aM�6$���
��w�W[)�+e9M�g�#@7��rV��<��1z����5��Ս`d��E�Õ����:�G%�UB�U^���U�U���B�
sT!W���ʪ�{\旎Ndm$��>��l@�ʨ����=Y����`2A�J�oS�r0g��?�~-O'bS�YM`{�-_����.U;�ݐm!�h9�I� ;N��.X/$��d�
�v �Bs��dV�� ���\OV���d������r�b��(�>��1Wa�tnda�j�h������ڳ
��%`={I:h��b�%��:d�>���+2�ᜫ ���qEW�h0�?�5mT �� ��|��J���SLf���Y�+��l�Z�B6���{�<H�̂���h�ن���ƙy�l�Ȧ�c�����*`���}���q4%QErE�?�=��k/�
�Å ӷg�=x*��z�d�o^׃ A�� ��ڣ�1�:W95��?9�GEן�PX�n��z8�:j`������=�%늣�eAn'��~�M�X��-�6��98�DK�X�@2�԰�x����,�ˋ�Ԣ�r֒,)a�;62,��6
X蒍4Zj�h�-���������fge�KnX~ψ!&��*��
��/�jX� V������.�p`^	Õ�9��St!VV�3q�԰`�.�r�X��R��Uw:\.�4WB�����z����� b-������R���C���5O^�ߢ�S����� �u�b�����9aAy�I�Z��QZ�Q��&�*SI�EA����O�RM��t6�} �+6Y����ZF���SU�P��o�VP��o^Y=PΜ�BI�SY�N��Z2�U�\�eXe+�q=ձ��j;�Ԡ
p1���1��B�z�A�Xʆ�Ҍ
��hC�K!Y;\6�uyn���Uc??�m���,�ܕS��Tid]���ӥ�Qጸ�`��<xe� G\�u�ᚦ<���ʊ�rpI42؋Gg.E!W��*p�������,%.T�@P�~��[���Pq���J�Pu!���&�*`Ȝ�E��p}d��^��%�������%$?Śo`��ɏ
���6�����ɥD����o����p��A���F��_2g�Y_����O2z-xP��t�}7:da��́���ҁX�`�� dnL-���YC��X�'�a�EļX�`�\$<Ǣ�P�	�I��##����,8bN1bM�w0Z��0��)���dɼO/��~O�E�ڿ���.DR0 �q-e�}0�����Jv��>ܸ��L���C���6YDJF�yL淐i��w$X8�̪���$X<D,��a�'��̩��/$X�`V1byj�^��`�u?Lh�!����Xvh��,���5�)����|�~5��S�)���R/bS�]"�<�����\��{���:�S�)���jke.h7�?�Y�2��[e��� ��OUz�����m .XE�q�I�͢c*`e*�	$X���`e�X�`�Qո��(�b���Q~O&��!O�j`��!��������C����׋XB{,`�~B�J`�E�x�cA-b 姿�Cʯ���!��t?�qU�v"W9d�j*V-ډ`�y��C�Ϲ�9h���U��1��BM�j@�v�z�"�F� �]��J�}OϱpH-�d�N���zT�0��,��9y�ַ9~�����9��
��׌��8��`}+���Go����f�3�U"V��`��[�!7�ϟu���E���z�+�w�_�O0���4Z�"f#&y(bG�<tI��1�8�ȓ7k�78�t���>I���>{����h�`(�E�`���a�W����$	����Go����i�M駏�l���CY��,�~���_��h���Dob���H�(�:_-
zN����ҭ5`(�z��pD��?y�f�cY�U�{�*��O�����c�7Ӆ`@�ɣ7��Ʋ*��)��ћm�}S��~��Ͷ�B0U�(h����̏�LlJ?}�6�}�L����ܔ~��Ͷ��a���%�
`S��7�o.G��n�>�@6��>{���ʓ�j�~7��>{��b�H���B�f�s��� {�C�7�i��`��)0�e-�:`S���7����r�%׊�e�+R`�d��j0`��>��!�Lm�Ǉ4����2n�ε�cǇ+(#�`ZC��C�Ճ��}X!�C��?IYV�̣	E���?�)�����,���y�s�k!�|(0��(�	�G(�&��M`�G(0��o/�U�
ڏP`	O~���W�Q`����(���2X�������,FG.~����+l��O�������#*`�2y g[x-I�ׂu>ޥ�l�\���q�w���{�4Q�ue��w��7ۂ밧��5���nzH8^��8�5�6!�m��]��[(x�V�����z�q����PI��v^�We��ryX���V�^�,�@qa_S�g[Ʃ[�ϩ���w�B�)���ڢ���>�<��eg�k&&W�FqE�U�G�
\S�i=�W�⚂O���|�JJ\		>�u����Pu���������\�<o=��}B�y(.�B'��\ ).w�_�}8/��yh��X�N�(~��:�����y���P\���6��8ŕ�ƶ(~���2C'�y��5��2�R��~�/��7�U!���7�U��=.�\��1iqy�R\��>�G�ᚆ��gw�R��4th#�wc~�p�*
���Cq�C�Ը��Cq�"%��W���A���Uᾃ���+����_�8Y����������*^W2t(�z�:1�y\�;�b�45.����E�
W���˽�o�pEh�Q\GCGm�'h�Q\�T�W������7ZB.ش��~N]�	�Q���,'�2�~�N���?>~����N�]      V      x�̽ݲ��q&x��)�jb&&JA��k�jw�HG�}�o���R�R��Jj�~�y�af"�C�$��[r�u�I�������ۏ������������>�_����o���7�>x8,���ӛ������1���t�io(%?��ҋR?������=�|������2����a{���b�TTK���ĥ���|�6��'���� �R�M�7��
@h��4G�%�����m��1�F�_����_���g����_���`՛�wf�ax�6��h�!�>?�Lp���|����!�����?��^�������k�q�����LЎRj����ͅ������H�9��gTS����]ѓD���2o?��?���~�˷���������o?������\Z{3�8����͇����͚?�CxSv����������;Ɛߙ���˯��b������0�����4=����?}���o���(���}�9k�+�Q�4S�	��A������_���7_��)����1t(凓�����TQ���0������~���������w�������_>������h�.`��(ׁ�e�� h~��4�aJ��!��b�������uX�os\�@?�?�xP�unNgcS��_�fL�d��uT]'_�������/�(�wT~,%�&���I��~ټ��'��N�NJV��������0���I�����"-��u��A'�ǥ%.%���!�oZ5��?��o��'B���ba��W��
"A�(�����"_������]g:�N�T�[���/~��_����_���C'�ŗ�|�߿���?}��w_*2�X����5��Ux=�0&��p�W)x���n�6�0�%c�[L\�Sk���7f'�iM`��~���>���_a� �ƫŬ���ɰ���q�A#`��~7U1�t����ڹ��~��/_����:��xLYb(�.6���N���h"�(-&�R����/~���O?��?��'k���?��
�,��k�"h)�ch��2�k���]��25�����ۃ��]�Nk��]�����ud��?�������p�Yp����$e�q.rQ�����Xg�أ��7�2���_X�!�<�,ɗy��c�!x�)���0�nZ������1��w�H�]����[�=��n��i�b^�˴(�k�W�����O�V�ݿ����o�ն�[t	͜b�t	yNq���u�aa�p���o�I����˟�.�Z�?i��h�1#���l�pS�h1Y~�a�������Щ	M�VìIS���{64�cՙ�[�ۜOi]�p��u-�w��}�����	����ՃiZq�}�L3kcF��s�dk7���?�៿|O�/�!z���T���:7��U�WC�S
��:������Yq�PP赮ʠT�'azi����5�4�a�B#�*�n&�_;ſ|�=�X%�H�H����,��Ɩ~ҧ�����n���d��Ͽ������������ӗ7�@	�����Nk���7�����ڦ�y>G���vH~��O�����_�cA@XM��7�W�KO��б��f3c��U<_��U<��7yiw��Ge�;�Z#Ѣָu����l����'	?	������?���eq��R�#p��BtQ��:8]�(��>� "�{e��6�?�d���u�X�'lY<�yZ�iSG�Lg���9��"�w�������
V�髟~��Ͽ���V4]��&ׄޯ\�钢��W@r�u��f��oׁ��X���u���g�2�����+�<\�R�3����Df�0���?��!~�J���L)�3��{YV?M���bGP�c�7�~���?�~-�?|�_�]�i֩I
�@+��[�A/�U2{$�^v3��叟��kga���Eu��瞭�:��Ζ�(�V�������+��Xc�Yx`�/#�F�^-cj��eW�����,��~���*�9R�(Ç�2n���b�������άc�Ml���o��ۯ����o�D�����ϯ4���<�E��%^��g����RfPlө�1��X��Բ���̺����yZli��7��[��Fז���Q��pn�k��l�����@�WH�]&��#[������}V�pB#@]}=���$��EB����P�)����q�H �@D6��.�=?hA=����}�"�S	Pm4�>�#�GQR���)j�L��(�%pY�o��{�*f�)ep�I:*�`�=מJJ+�@��@IȚs���L"��Ńf�Uf��s^���9B�ܯ�����CO�	fX�Ej��P
|\G�RAߤ0e�mf��kN%��S10#��=�����_�3̕�}��#Ћ�d���V��@9���C%��Z��r5nH�r<�����Q�5�k��0�%D)_`��w{Pz �H��Ԁx�Z��3P�ej�+f
Kn�f����颦T�9$��7�eխ;CņY����Q�nl�)�����4� pjʩZ���2��g�pP�+�jx�p����]�e�L���X��[x���<<Gan����U�Z�WCz�M�?	R�,����}�.�qxu�Eu�~�A���5)7I/�8�	�0}Y�wͷ�C���Kk���VC�����:O�$����B0�Z���r��鐮e=+~�A1`���p)Xe�U�è"��x&$NqP�Y�h%����8Ms�)eP�)�A1j� o;�L��<Դ7�U�]�3�Pb�
�]��k��%R۠6�����^f:6�#j�h����ɍ�l&��z�&�mMXc6g%���M�(C�0����wcb�*��L)K������/�`Z�#�(�o;V9p���v5����5�ՇU���<soT�6v8�q��3�<�aq3d�Z!��y�.�n���C�v#���McJ�
�l�4SwY`��{������Zb�(%�0�7���&�|��x���7�NQ�.R��o�b^B�u�M5�o��W�)�v8��Hs|��w�n�ܰ��U8�BŎ�+|�h��^�1�_�y�/��'�O��
�
ـ{��l��Z`�3���Z��3��m`��ڲ�Z�eb�Ҭ���j�-�'�$�h��ek�;�R`��P�jsۈ����$X��r���ǔ�2G
��'J&!mGwA9@-�Zm#H�R��)�2��M�e��ܮ��n����â�)���]���۝�S2�JW��ȟ!D�IF����^tS��=�9u�6�ej �s��Ѷ���.���[%_��N:�R -�9�����S'Fe�#�5�����iG�G�UA-��>����"�d��!`M�Bg�_t���}��� �s|����N�dV�v�d����W��С��|�!5^d�L��������}y��pݐ"�M^ϑ�K
u�B!�ʆ�Θ�9�׉�)�M>,�?'�c�o�/{s��_}PkH%��E׬����g]��.W���eGL��8A��X�MGm��6e�k`MOz�&��(�}� �C�gi�l�J�]���x0	��D�2�E�����#@ƄPR���) �
? ����y�/�)d��,�+ơ���}v�f$y�0&����\',L����L3ʨc��6����DF��\����y�=��nȪ�0'�Aj�
w��6+,�ݮ��Ӂ1��8�i���"�,/�<���/�-~���U^�\Ѕu%�����.
�N�M�j�V�=ۚUr8!	UE��ЎţS߯�*3��x�`<�����+^E��R�n��hd��P@����9
��'� �	�~��%��nkB��q]�Y'�@Mq�οć�R�YnBBľ,�@�� �N(�����Q�((���|!�Y@1����Xb�ZT��ޢ������?���������?��&l��$^�I�9�o�[��IMf����a�O�X%�놅W�k��*�ZJ��=�fl��Ƚa�6#ҵ����6�'������ɡ�Ѻ���ĵ�k��L��>��-���U)�?�^�OwT3�=�E�e�k��L�Y�Z��Wq+r�W��@�     �BM��UV����ҙ��BP2Z�ϗ�0eĶ�>��l�ZRM!!�U-Q�����T��6��ε��VqN�\�Id���מ���8�_h]�]�Z��	xXzwi��-]�W_��3��W$�N������a޺A�zF4��m��!fD��ȅ���Q	�M�\vvT��.}X3�T�8�T ɂŃMپ�7��;�
!��n3�3�K���dTH��O+Qfy�P 7"���\T���\yTxuf�w��r������M'�^�<*��v&��A4����Z�I��vrߨ)�p��p%�ۿW�5�S6���<�=�`�G%h	�v�{�F��	|.��b7�F ��x��oաxv���G��2��_r"�[�jP�N�>q!���}S:(�^L�&(��M��@�lE�A	�ڻ6�A���Pq��I��7�L���Y��5`��L���Z�����t@�)ir�9< �=�G$�����&`�>p�k��܃�ɋU�*���JT��E�XuL����
��j$�<�q�e�ໃ�7?_�#�|�� �:2/�(�`T�>3Rہ��"A�\g�~��f袔+�AP�FB83�zt�K�RI��{?�[J0�^	����]��^AF�b5�o���� ��xga�&����A�{�<m6j�t��co�,,b�@j�V,��4��"����W@NQ� ڼ�P"cV��P�ܐ�xo�%���"W�~��#�6��� hӡ�S��2[�ʟS�GyVz�) �&��{�t u
�?� �=�
@��m#��'�g�tl���Yp�ڙV ��$��(X{E�n�ю+��k~"B��s��2*�qzIJ[s.v`��Ҋ5%7��� K�.���A8;�C���w����:�Ʒ��������[�h��y>f](rQ5r�Z��](���H�,�!t�c �X4r�{�qҸ��E�v���I��SV�`��,ۏ+#{p)�>�A��dž�}�#������19%��$ʯn� @���tĻ4bO�j���Q�)y}�&i�W�m����Í��ԅ�@��1��zcs�|/��D;
�ھB�E\~��<�:]�e_X�)"/��p�>���y
6� o_�!�;� �������<������
+ϊ�}�eg�%�뷡Y��߽���w��Y��# =rP&�����q�m�#� 9�A�A7���5)��ѳ]���^��zQ�V�ut^c�Ck�>a	�<Vr��k�{:qE�
]QEk�>b	��
��ek7��4!x�W���Z��Y�xe�4�3C�oR��ԓ^6��e��5�{����%@� �T	o�7�:�7g0�\����٦�X>���|�ӳ�pMn���(!7c@��{�����Qq��;���3Fљ��=�^��(T���g�Ŧ��.}D0�	�v�	�ȧGS�!��)/����C� ��:���P�$�� ����|UR�j�L�#� ��Tf�R�P�g�%% �����f�S� ��e}D? Q���/�bb�kW��Ƈ� P�>��jak~�����mFkNk���d'dd|> 8�TO�^Uu^k��cN>��)Y��Y����k�^���eD������aFQDh�K(�62l$7]�<kd{����p+��xb�RN�Lq�*�eo�>���B[���%��8�����e�� @dL�q��	����\���=�-ӧD瘴�~"�C�0��]��% �:C٢�/�;�n��u��9+�6���1K KQ`\v+��JҼ��G&̥ĕ�7Q80�f ,č_��r�JI��p,���6�c�����[�l3P����@Ugx�F5�T����-e���%���s�*<�c��G��ү����أ�I'����l Þ� 5�U�|*�i�����wp���S��]G�m����].7����Q��N}�06[�Q����/�A��tIQ{3��� 0��`k���Kg͉R{�	��j�3��f�󮟺cn��)w9${���3��گ���*��i�Bc���U�8�A�i�G�ڇ�uh�*(�`o�>$PA��>23�*p������b��{W/
l��$����%u��kg��H $U�·Z�����Sj+'B�eo����{  4��㗭�{�V��#���t�����	��fV|�Y%VhԾ�9�y�� ��VQ�A4�vn��s�(@Q�hs,v�'�OY�U�*��Ł������k6o��#_N��A�D�����@J����?(յ~c�B΃-������*c�\υ@�j!C�$4! �b��  s��;�&,�X����^ߞ�(caW����*�l8��� ����y-�ٲ�5?Z�,�C����ko�*��1�=�/C�@_�����[��qҿ �[!�X�ʿ� 'QA��-��o���3'�L�Ar��+�C(ۂYe�����G;��Va�k��ǻX��d��NW��kd�|PL����5q�d��aI��Ȓ�Ӓ���1v$@Ӕ�B��Lk����$�x	����v>��wl�Wj��Մ	Zi.�,/NDP��/UP�9�u��'�I\�Yz
ƿN \��PtY�ᄗTϸv�9̤���'1��,����W;s?6H��,�Ǔ}�#�:�����*�i�s�_�9�Vx��(�@�.`�E.��%
P�d*bm%w��[�{P�� �b>%��C��� W�Ӹ�L��h|�\w0f�U�!gn�_���{�_W���;@ g�zK����s�DJW���y_M({}��r��$u�� 
?�* �r�V.[KH�
��N��ҕͤ�������Q��埰K���{���W�Mk����v1L�p��ss�5.�
���;�3���A�<�����<��S讅�/��d@�� ����o�0X�L�	x�JF�$�����͠#L��3����3��%@���Ȩ��]Og��Q:�J�� ��7,��v�۞�"6[7�
 ��}��� ƗwI��FQ����#�@[��$�
ymF�=����0� )�,l&���54�����]xZ�yw��_��Y�� �~Ҽ�?�3�&�]AJE���ɾ�vW���m��%L���αW. H~(/�@:��7�
�J��� �� �hs�zԠ_�2K���D	@rA��+��ţ8̞ͤ�l4�佤��(�@�!k���@ع`��+� 2d���H9Rg��'���-��1�b� tJ��[����TM�7 |��~���c$�ܔ�o���'[h�q��n�/5|@�}����Y�n�-�R�5E!0!��\"IA�
$��^j��TT.FCY�\��P\ؗ����P�T<�hD��%w��o�Ci��l�s0��7{!(�՟+`���`��uGƧ��r�@F��o>�. ���u�]9�BsR'���P�#	��V��]�~����2L�檬�;�Vf�y����t�*�a��� �l|��6>���KՍ�����ل`2/�Mjd�O�����g1V?��.	�T^�,��v�o��aր� �66��ߔ���'��/� (mb4�c5҄7��ċ�s��c@�6���X��IƦ�j�Y܎�1k��l�WW|�Q@�� 3{d���"��dFYz�\P�S�K�����1��:(O�w���).X�MP�]4��2�\e����vE>C����?����Xq��:.��=�hջ�%�Ҽu_h��v���y�9��}o=�Tպ�o��2��Abn8�R��b-[5R���0�S�R�<�ag�u����a g�p�𢡄ͽb-Z�_<�fi��g "$�]"���� vo�֩�c4~2r�BH9Z/�ώ�Ӧ�t <K���6Z�O7�?܈]��Ien9�:< �դR�0�~w��(%N�T�ږ��� M%���^���=8W	 i:�n�R^��;�;������5_(�-��f��F�F�m�?�R�^�ŠE��u@� ,��    '����/:@1��1�cǩ����$�C�k�`����;�*���:��o��%_	<f��`�|����� �����H���	L+־�6�f-���5�ʬR�O���O�&�U��1 7Cl�� ��`��,-�hʥv��xO��$��ɐ<�>#�/|�*Cj���d�O���c�� ����
>����0r��i��/�%�ě��Ă����7GFK<��T3'��l��.�!K[��p���%e�����5uX
��i�I#y7��
��%�C��,_��8.C=�`Qs4V��p�)s�U�D�xN�:����-�1�RP���M*:�����N�Ip���Ll��-s���4덑ޡvj���%k���G �r$�B�ʊ����B�K�:�>q)e�:��3$�pm�z�#�m[�&�\,�\���/��}�K�p:E�	��cG��L�<E�Ͱ��vx�������K�<�!�0��pe�+��dS���{d�s��Ɉ���By�"��"h�XaEpخ��ɦ2ۖ�B|L]"�=+:�s����;#��҇��K_���"�h��5���3��h29��T#��Rs���F��bB�ծ)�7����Q!��"0�$bY��W����S��	~s��v2��\2 ���|�����|ӤϾd ������?���σс6�C�=�y����/4n���BK��a@���
`ɘ4���?�<�Bg��"4Կ�U�7xH-�!�p�vhvo��BwN8���n���ê��C���P�3��HmY7�6��:k�)�t��C0�>�A��G|�h��c-�6z�#
�T2#:���q�KV����:���c� ��p(�,�p��z��@rB�Յ�?��۫����F���n(��n��N�FJ_kwd��*��m<�N/�Ls���v��iu5���90�j�Y�Z�����r��ƣ�n��r� �m�� ت"q��dr&�
y���z�Z��.���u<��g�����J��h
9�l�-U���Id�46�PH(#sr����s�I��AyY˦m$q��P����]�47��|a 2�t*ć �<��F�Bn�r'�@��i��{���usP�M���G���V��wA�Ɋ�!<�E�F���P�F�QYݯ��ww���>���I�gh}��P�K��y�y�ьΗ�	�`j�Vef��J����6�S��W�2T}Q�I�i:����/�M2(�w��a`�^m�uij�ϱ�*�*�>B�hG���|��$�)�}�q��Q�4�♮�� ۱	�ƙQ��e�ƃ#Vy� f(�pV'u����s"��C�t�֟����U���egkT�[��[w�2~��8�D�g/�-Ϊ���@HKȱ̮bVOPӮf�
x2�簁e�X�[Ͷ���JE�E���I�E`5>ᶁ?�����ɛ��<�R�j�/�pp͌
���fr�|�G����C1s܅C��dx�8��۶�[�J�.2�CvC�N_���a�[4��;�/i"
�"�$o���l��.6�qC�c|�W��Pk`:�[c�1�Y��S�5� Q��e!������i��<�+n��p)Z����_�PzW�~ڍ��^Wm@���*U D�!+���
����q�~��w&��O���� �j�@[�ٱ�Q����L1�x�O��h
h�)�â���F=�,�d��<GaY;�v�_��q��]L�嚏T�1ST�j��ocP� ��[)v���'�l �]�>@1���ݍ�Y�w!n����˘�Q%q�:��������i��4/a�2�AĄ`�&Ch�����ʂ�"`�%- ]��%슝�ss?6�"���"����A���,�+���x�L�S�f$���m�/�i}/x3�X�]Ƴl�6 @����A4��06a��Q�̔LU��~������sP۽p�(4���*1�#H�@C��0�jaI5�|�� D�jsF�� ɼ�APŃ�V���1�,.���O��E�CH�6Aϛ�����q�����bJ%3�87�>�ҳ�q�]��Iu{Yb��S��[����P�����1�e���pϥ���!:�i5�U�nQ��o�6>��9�t��h\U�1�6遰��O.���}�G��я|ܼo����.� !��TP�v�P����ά���c�;�)uÉB�L�g�!�);6�t��k"_~�CO1\����S4[C�?bx��̶Z��<� ��;l��U��h�>j�P�0�)��-r{�1؁p� ::z�9�?�����Ko4��C�ښ��כ3����yrr�zC7���ֳ��(����f�a�/��o���8H_s�o�
J�����.u��ќ���kfH�l�����K����i���``ŋ�sa���P�����-f���}0�cz��<]L{B�UL����O3^�� ��.=��(�Dߥ�4�.eK��R��V�M�O�D ��f�E ad� 4�d#{�8V�}�D�  D�vDhDp��u`؜�~�HN�0�����P���e�~4���O���ٸ[���9��U��e��Y��0�(<���Ax����`�o?�b���,<E���.�;G����z�PE���S��VL���a�����i�������_�����k�|�b�X��w6"A��|}�#h���ېV:Qk�i�;,����c �F�#RA(�T�w�g�֗�(����xw�3�
�AK�1à�����WsXP���w�5wв�C� �wŉ(ʭ���Sҕv,�v��6�yE����JXW<}�Dߣ&�	����4���}HD��%"`�qY:�(5]�����N��  ��O�L�q�:�7��/��{Ɍ�o�#�9��B��C��-���?�']-F �g"�3��P"��l��K�����w!�N���̴<(��3����8\v��o|�CC<�b��;)-�'uv���H��%�Y�{D��aɬ��
BAC���UvQdﾴr��8�	�����D�ր��B��0��%�
&9��6b6�-�LBt�rZ���4�����������Ҏ�����!���m��	������/l�a�4�Gz�x���f��,�k$B
"���؀�PD�;��~��A�Q�..�x�6�e��掉lo�?�G�jDmo�ca0u��֨�ueC�ɃX�/�4,�.a\a�ou;Uţ9;�C ���܉��$���v��fh�aR�?�[����x�������A"��@�M2�	��,��1̪�ɩxbc��R���u�ڵ��<.�io�S,�+��*w�<�%�������I;�+�eo�?&[�2�Z�y�}U!A�g���l�{�^R�9��#M|�vs���wL1o�� FQ,4o��V�� ���[�` �<���!`��@J�^�6�-��r������B�g�9�E��J�[CN�[���?x'}L�ۓ�[��/�� ���K؜�E:��;5OYT{�������;b�;�О��ٝ�)�G���T�L��ԯ��6��n��O}D\�l՞T��uT+���*�fI1Z��F^E����A��;��!�O��0ٝ���&����q�P�����]��� ������i��'�;4%�=��� R����1�Cp�����w>Īi՞��儠��k8$7'�X}�z�;��G�g'\EPp�Ņ����?yd_a�16ߠş� ����!i���Se$/z^��K�1�N�hs�b���γ*�D;捴]'��������\�IP|���K���@��u���d����m��#���ˮ.X&�LL���1��s��K����\Ή���9�0&�����A.A�.�t$~.��H�盞�����ļ�$��&��}�YN0c|�����0�)���@�ARO!�E927x>�ćL�oA����3	j���O��t�kj�d���GȘ��'.��{�j�C?�������a�/BmI�q�x�@��    -�����L�8�M�����Ji��������#�C���!5�I��\��,ݽ)��qN�2I
Ag8�DR�S(����ą�i�DFm�
1��.'��ءi?�"�D��9�09��á�9 ،�.p������1��rk�K��)��\R?,{櫊�-�'�&o������Z�k�	����sL�P�S���J�},�o>���WW���T]�ZP�5�7��VJW�2�?�(��R��}��^zT��ʕٙ�>/�*�@�0E����*�D�e�YQ�y�����E���BN.sz�e���Z&M�8<W��~�s��/�q�z=>$o�sRX�r���N�=��{��k6Ȣ#���G�p����b�� Y�ñ.�;?��q�i��� 8���R-�,�{��k�]���4�� rxq�aZ[u[��V��ǂ�ʗ�3"P�$5U��e�X\8�F�K�bz��[�9AR"�*G��2��j��^�=��̭V��,~���q2�n0�@���^��xS+)����$�Σi�r#��\<0q��<"5Ճjh�{Q-$�&��1S��ZHx��^<�f(�Flڕ��`�Mb��c��4��D`�k�õ��TÒ��d�+` 5f�q7�Q��ܴ�NXX��k��Pq����P"�iҐ��M�8��Ex�s���� T4a�P٤���>�,G�g0�]'�N�(t�Q��t�a�;"��zȚ����}"����+A�9,���'wx��(>G��&��G�zrC��≉+k��$K�3!j���׿�9��D
�\�=/{|���h'CЙBCݿ�
x��G<���XemW�_�Z{Z)*F!8  !<%֞�2C�p�}��j��QPȟ�zޤ ���� �Z���������&���R �	�A�OV�����rz��(8���y��E<�2�m�q�y9��Zl�io����BZ���۩`�N�?WF��n�J�C�U�Cr(��ܽH�.���~���x�]������0H��.⌽Yr*�.�Ò��%�zh��-&̶��,w��s2�W.��7�LɅ��C i>�n�o��[�	3  XF��;�7��lBo�΋� �I��������,���N��.��������QxpK�� �+��>;g��ɀ���npp���ׇ�xX|�j��Mٯ_�w�<�M[���
��+���v3�e����x
�$�1D'Pz?g�[�8�jBg�>c�I����16�%C�4>V7Q ��:�\dZT|!��)���N�'�	���k<ē^���ͣp�-3E�s���ʜ�c<q�Z��jZW�� �F��C��u�����u���F����	ӊ
�P�=v���U�]T��]�a.��@D
s۝�nS��y]���-}S�!�������m�|C| ��sn6��q(&�vMg���Z��l1Y>���F�h�b;r�2�I��׃�0L�zB��к��ӟ����PAĩHe(�F�С�};1��4g���4Fm=
1U���m�1 x���Zb^ ��%c�a��fy���&x�G�x@�����,�6R�֩�A ���%�����3��B8G��)���*���qXְ�$҈�D�]�\㖽�НZ�?;�l6jC�F�\;��A��J�b� ��NNO��LЌ���x#�M�>'DTmxâ̂!���8��b�s�r�1����2<��df����� 50:=�����j/���hR``WO�"����`�҇Ej���e��g�Wẝ�Ǒ2�$u���Шdy1L�҆��
MH��������
���^�]�e��c���������z"B��H��}>�^//5,�o&�c/���	?�^��N�N��E�wcch�*���1ڏY,��p��nq�&����6�����1?2�U�:aR�f�5�6|�Np�I���~��1��_����o�U��f1��E��Sf~�FkB�}G��]BK�!W��E����$�Z/`�,���$ �3[�Aq�h�v��#�}ـ�i�)���o��&���׼@��<�ʡt�����1i�W�4��dT��ë�	!�,��� ä�C��.��:��)����Ҡ��&�kGiT�֯9h�􊍻�+W�lc c�R������?�<�ŧ��f/ې�7����/]�l��v3?��#)�bC��ii	Ex�Dw�8�t��+ <�O|@sEa��,Ok�C�\����]��y��0h�������d ]x����Xwϓ� L�F\�+6��/���9�(��!�t�Ԑ�0FA*�K:���q��0I"V�GU���F`�y"��� �ўG�����	�6��a|.���4�o$�J����EV��o"|�[��lq�WA�n���`��0��֎�J�5HmsԙzV�;�]���@�n�/HnN�̀��X,��r>]������-^�y V�)o�3�2#�K����Ѫu�C�.`�ՎaC�}�R$�eM`������D���H�$k�'����gv8d�T�7�l��Q��j�u��Q^N��e�]��k�+�>��e`$4[V썙�*_�%�zi��ʻ%�Q��O���6p�!��"W4&�l�`��d!o�e�y��}:w�;5j���nf���;�|7V{Z_��M�q�Z�!��,���Vh1bL��cȗE����y�u�1m�6�&V5��Y�	47`���R����`;ԬR|/�DZv^�E-��Zd��� �խ+jry���^��hS�}؈ӆ�A��@$���]�
�� �m�*l�cSӪ�;6��M���^:�f�>��DG����{��t���d��a�Hxj������|`�$���7��ޚ}�>����8z�0a"{Q�L�ըވ�:B�L8��-����2�>3C�A���i3R*����#�݈��)�+��a�0����4�݁(ܰf�鐫��/���ë%���fH6W�o�D�Eb{N��ڋ��I�<�p?��
N�w�_�͑�}hဓGw������ M�	3��ܲY	� �����1�z�!�n��������;6o�o�;��TN�
C`q:�����.��ā�yb֞J�V���ť?�Ch��r�Cȩ����r����[\����T����p�hݧ�OA^����`ɽ��jǠ��r�])f{��*8ǜ>�~�"�h?tC�	w��X��U����b�$kV�c���N���Gd�ޖr�|A��`�$?_ ��訂�
&�86;A*JlwW���^U*����ژ�N*(�j7�Å��#�]R�����L�(*e��Ό��IH�P����i'8C2���������I�j�ؔ�wj���S[$�VO�
�-��8�
���j���j���������Eۯ{�uJH�GC�oY-g�V�Ȏ���_siXW0�^���s��FKb9��B�VZ7�u��e��HRPg�1`��+�1TC��EbŢ��K�k�ɤ� Vzu_�R<��0*��.nʀ�Ebe0M���cU��tm��2�_�z��pbf>�J޾3 �}`,2�)�\+�wª`��o�0gd���� �;�Qչ˘*��6��a��l�@��832 �l@��!&Cr7�p{J��g� �S�O HG�Rt��@���ݦP0\�Nح9�"Vq6�J��q;��y�کf�q���梨PH Kk��*��O�̘4G��B�2C���41Tv�9K���e�J�po�`c�#f�E��⦛D�-��x��`�NI�1��G䉫�)UX���<�-m`�1
�٭�L �y%p
.k���z�/�lD����doEM�˹�J:�u�Vy-�������GL�&�z��^���I�ס�F���:^J޲�#�0�rpk�DY {F�)e�M~.�-\>�ެ�4��@���`�k�w4��	�)Yʪ~zd�N8�kXv�o���a.��2��?����()����������4�JL{��q��^U��T��ԥ    �nL�Hye�*8��R��W���hN�p��GGybO�G)�������x|�k��qb�4"䶰v~�ޚ�� �=`�2O4� (G<�.�_c�$�#����(�=�a�y�����$��Cy�do��32pUW'�҆��/�x=c@R'�T{��q4e2�z�˗����w룥���$�f�A(�L{ɋ�����������c�{C�A�B��[]L��^;�eNqon?fKxu�8ٕ�eb������(1({k�U׌������r�; aUA �޶��|�6ܨ|~-BBt�(&ƽ���� `0����v��IB�˥�U"5aĽ�|�'�.�_m�S�}J�¤�k��'��X� Ր��Y2\�׬d ���'��)o�Kь^{+SZ��D�;Ǔ�'f���SC�r^f|}[#�</q�yB6�`4/X�f9I��b]e����CW*�B���]E�i�e-�o�b�6ZQ�`�R�fG\���)���r �%�V���0�y(m�'�'��B���`/�A�}��ic��}�����HH�`�!��vǄ(�XI�s�1$���ɺ�Kt�m��kn(��� [��};���/X��ٹ *�Z��q`�gx0��	N��tY+Z�i@n.���|����q���<�c@>bOM�O��s�K��\�_�"J�PS��"Ű��>ڑPR��B�ӎ�xV|��mӲ1NL��T@=�$�3�e�Clq�bƃvǕdټ�>�Y0b1\��+I���($F�y i��b�1�mq�Җ�Bx0�3G$0�E��d�*7�#6W �LYȗŢޮ|����h���2Ƭ��x3R��e�]�r\A��z-����Fh�\+(P���
�� Yˌ�����$�N�C���v,ba���"�ikY�¾�q�i�U����a���:��+t~g[��whU��;�!���3dS�>����D��mZ\��Vp�6'k�c�ti9W1|�V ��w�&���F���99���ʄ4]Z%=҇Y� ڱ��Z[��4�_�9�I���V��<?�d�@{�nz�z�n�}^K�d��x��,`�W��xAP���CE{-(Q� ��sqO�Y��@�R�h��
!,�>0vz xH��jl��!^/&�Pq�Tm�(5��������7��L�}��,�Q x��b�M�
;~D=�9w�/�w�}�ۘ��@޲����#*����@����։`}��A'p�]� Tv.�:�ѭ�Aa끯X=ԬrSذ���J"��͸�L�z���*:�b�\�O+2�7f��G�u�>���O�E!_hv{<�!��̳x�{�37KP��%���ʀv9 8]�����ߴ�UL�w��^|�d}Ŷ9tWF}�ISҶ���J3�6���8� �r�+v�_��ti�� ֕��6ym5���U\�t<�C�|���1Ci�{]�O8Ӡjj�5��G��
�E
)+�-;�˹����3
��W����,���pY?eO�6~���͝`8`��Lŝ�B#�A4N�j����UX����-/Q�~�]	\�z��_�M(M��y�W��������oN���\����RȜ6<�U��2�~�!�H]&(�g����T�*�ג��(v��O��IGk���eJX����K�f�s��,����p�߹2����G��@sz`�(��t�����x�ܫu}����i���A:�#�蘢��y�=+g4�"i����gK��鴴NǴl�c'��;��-tMԋMsx?Gۡ��kt��8�!Гn,�VL���m��b���rw ��^rdA�+��^>��(0��	���,K�W������H�V>�sw�,���Y">l�M�����A*�p%P������yM��9m��.��Q͇��0¼Ct��wha7�.aD��!#�qi���u���qd�#� �t��±DAAv:~#n\B�����9�̺�p<�ưskA�f��4'P�~�
!V��i)�}�t(�+g��B�B^�FZ�g}[�����=!)�O� ̜w����o�8������<'�>��r�����@�A�ߢ7�(�C��e����C�󃋘eG��,�`���7.Q��TY�*�gM/�MF&�+'K��]�J��zڂ*�;�! *���%���I�6�^&��K��*P,�eP�[�.PC}��"[��m�O���f+ go�?ru��R�0��`əM����s%�,��.�J��N�p���dwRH���So�N?:m��Wx((���@+����	�׻QI\�2z]�:/�K�ޜ��ba�F���7��&x��1${���r?�luo4 ��>;���nA=�QC ��v̰��uN/�)�웟�Q�wn/���n/L���w�,h�n��׿m\p�s|! co��)���h����4����ڀ�	W ��N�����@ k&��)�o���[��	t{Z����P�D*�p�Q~o�?u��/��_���C/�,{�($�[8|#x/ѡq�����N�i&rR�����.0��_e�Ô͐���s�$x�f&fow8��T,���"�Q�]Z�Fn�z	��[�O�`PG�A�h^�W��*�e-�`O�[׏a��zS`��
��U~oh�r��4�0V���8�Y
���+�o��x�f��Chє .wR������Y	)T��E���P<b@�<b�qs�?m�0�t>1���c��Td�#��Z���w��y�x�#,^1�n��b(����B������4�	�.�Ÿl6v�K��OG�
xYW	Q��_}��,����mc���^��� W/$��G��C\������5�h����۷���I��I/�=��>仭a���9l�d�c�j8G|dc�"����a��&�Q�c���@�1��@#nS�{�n�hO;~� �8�M��xr��s��%ؠҫA;؏/66�s(���b�Lꛡ;�4M��L���w��C} ��`�T ���ǧP'��)��r���g��ׇ��%Eq���?���Hn���$��p�%�!�l��\V��M$�b����ۥ���1���)�-�p��9w�}(���4r�/�H6���ŔX�)N[���3��<l�{(��*���jT'��%��%X�M���VSh/��T[��֗��gM��,	���D��C�Ab$f ��h���+�vz8Y`�e2�fx�n�*�T]�t��@�U,��"����P^�d����C��'6�&5M�(�ʀ`�/̚� �E�j��9}���Q@꼊sM+�]o��4�`�mnD	ē�%~������D��N$\Yf-�a���[(TTs�̌i4�q�M�$�}�x��V��ϕ�a �Z���������5@��L�h���b0�O�!r--<�R������^�А���P�&L�{��w��]���R&�KW��7�B�CI���XƘr�>����yp�B�V�����CA��q���� H��ZX��<!�E͐'�ѓ'T�^$�wK��W�
�8���!KaQ`$*�c�����U��m��x�L��ծ=@��|�����+�ľK�),D��)��k�_/] ��(���#��5M��ĸ��WJtk{B� ?K��l�����Y�T���w��6`�Բ�?��T�q,�9�hݨ�N4Y�eLLl�G��J�\7gQ�y|��NĊ���s� �4k�p&���?m���y�S^))%a ��Cv;ecOg��\�+��Vs�9u#�*�1�osߏn���ϫ�M\�� ѯ��AT��(c�K]�!ы�078�xrYJ[Z�2P`�r���%W��og: Iv$a�oWǴ�Z�t\�R�� $����]$n�5df
[4XA��xUR1R��\̑�n�5Ș��.&n��u�v��s" �D���BaR�� �@���
��xZ�xOU5K!:�\p�C��ӂ���!&��7>��w����.F.�b�o�h!����ﯵ���32�Ws    �A�ll$�����5�%\˔_���88�� 7�Ē�p*c굱%�2�
Щƃ�A؏��i;b=-���)6��l!��Cd��O�x#!3k���Ud,=��P�O��r��y�)�?��Qu��r�i�~�>C��Ohy'�ŝ�`w�h�s^xLBw����q"Di&Q�x�� �j8G��7Ʉ���n��B��ؿ���Q�!I�Y�!`$��Q�zgpL<����pӫ�Q�D�@e��!���F�(G�
�Q��d�kL��ѭ���֧8�t,�s�������UJ}O�ۡ3����p�v���D!#�w+Z�w�V�l�M��D(�H�! ~::F�I۵$��']�N-٫��C��a�r]��:��
�����)�8�B��ĸ�R��c�-�yI}Z�t#�E���0n�*\JR�u�ٵ�C�9�n�j�RG��&f�"w��V��P�`�}�à��)>i�\�h���z�y��AIV�Ƶ�o���5/CU��W\�G5�r�%�	:J��v:8)a�V�W���wIjn �D,
�����@�wIjn��7BO��يtFw�1,HL�H�T���պ�������\�A������a�Z��=�z�5��M7�HD����bH�I|�EB(�r3pD�Vm��A!�QG�Ob��"b�Y��h��p�3q�|������/���g	��_K���DH���3a!l���>�m�?���Ijn �u���$/g��<f�������P_Jԩ�%�N��Gy`�<k�]���,��"��z�߅�N�Uާ�- �pSH��m`��Q����'s��.B���C�e6w�����,�p�= �U��&��C���CUoC��F6�pR��e7A൰4y=�"�����*4����q�Q��^#V�:3��.�{�j�{�
�/�Z��)�Z���C>Z�U�}(n���j
g�,��Lr5/�vdf���B�~�?m�����]��� :jq�9�~���CҼc<�H���u52��1L-��]�qVc�o0tS����X�V����ki0)��</�d����!��K�<��b�N�������u���w����d�����j"(���cb�Bc��������Wig�`��	(�I�A�.��S���^��F��3V �#�&����G Pw�@��f���yP��z>P�X����g�.���+�G����b�[�J�]X�Ք�<��U{^��
��/p��o���,������^s�mϼ�(@E��t�]X�2L��x���:��SX�W �+�����.a��׭U�@�f�\ܪ���q��Tj���7f��'OYE�ݼ�D��E�"���fX�;hx�%�m�9��+54
����I!	�8��f�;{s|ӓ0��"���g��Pi�Ӗw^�E�
�mi�>����f�k�76���O��9ЯkP�|�L�
tw�]�����:�� J�DfE�e��P��j���r��i�*�b5��r�6Ƅ���)=�3@�����g�%>_%�r���ZXq�)�A�ni�5[zx�:y�aJW��+�f[M�Sv�wP$y��Qӫ�����]���U�W��% ���Z�v1�b6���'i� �4��MՕ���|�}����Z� �0AF�E^�Y��Ǔ!_ݦM�B5o���-)�_ȳ���N{~�|ҡ�k�Zɬ�c� '�"i�v����,��H郦��e����K����_h�{�Q譱<����^"��C�������g*dv��MTo��@��P��>A���@�@�/�������Z=��ʋ������;&�,���-�rC���|��(�}����L�G�,��Պ#��.�5 �D����9���W-����L�������C�U�nm�OA�k�B5��eȥ� �[8�X�Q��@���k��OB_�����OE��|
!^P�-��#5��Es�!��p׸����:����cQշ�Se
&_�:�����IS9���7�Eȕ���_����o�5��:���ּɋ�S�<h�"�#���+�G���A`��ƞË�5�39pj��  ;����v��b����[��
����Bs�-�;���9�0L,n�`{�p�6m�T���ei�m}���'�e��葴��ބ��^C��|����v�/vD	9Hv�2��Se��D�ņl8q%�{�7ͬT�b��o�1�\�۠��QPѡW�$�4����r5ڊw�PS�M��^���V ���=��9�O�;����9:���hx�8�;Z���@J�u�L�r
+�K��e��R��Y��/�p�;pL�)\"���{m�GB��k�p���Dͨ//v�9�钛ANT<8�@�3n�L���pG-	7�7����a�dC[�M^��!S�9_�Z�VP:��G
�Y�±䱙�sG�Ūٲ� ���;�P�K��������fo���'�+���z��v��s�� Q`zڿ�15xE!���Ӓ-�E3m�o��4;��`��>��l
��P������z�x�{�; ���
����&/&���͖)L��i�Ck_a��:���]����R��˶�T�g[�pNx�4^�
E��>�$I��<�%�oT
��C>y���f�f�&����K����F��6��z3)�n�O�0�v�_�6b�Z����\�Wg�v?�t^��:���8dN�f�2�Oqm�
[�x������~�6>��x�j�|$����Ҡ
��F�����Q+o7	�!K��W�Qt���x�E������TXpFn6�씉n����K�~!����
W=�;�1?2#��$j�c�<Fj��/����0�I���ڂ����}�e���:��b��h[�sk2�ן)�	�V��mc��B�7|�}����xo�x��{~��\�V�4UM����Js�y�f������:ړq���{���6��XV�����y�A��g�/@CS�M�Y	J����gٲ��X%w)�">����`Л$��}��;����޳"�O8EI�>[.�쐤��)b��8޺������I�]FW,���m��u�b�'��׃�d����۰e�s�
[����ST�	��,K����<�&�e@AX0y��#�WX`9J�1k�#� ����.5��G9����q)Whѓ
Y�K�=�=�j+\Γm`#�Rk�^e���"<I[Xb�:p.ѡ���b�
w<�����IE��A��J��/�-9l4.����a)j[�4=A�q:/9=�{-S�aڠ�QOe'���ڮ��?��,$3O�A��ְ�0�/Ɔ'��_�U�^��\g���!�����Y�A�x�/��?�i��3W�O����6	�i49JK_�~I�!��ߔoM��Wu�g��O�WT��&���&����$�w���R���ڃ�w��4�8_�b�zc=����(�0���Q���n�80���Q����n��C�
H�O�(�r��C *�R�o�;�>TaF𔍗;XxEg�
�p�C4(�d}�ꄦ���֮��a�"��M觭<�-l��{8cؔ���%�y�*���e34r����f��(H��O\�ː�(Y -1eH&*�R�`m���l��
���V���K<����wΙD�o�''�!�8�F7��Tз�z����a�|�#~P(�Qd-l|?�e��}�	�zz�E L��@��Kbx����y��t���Ē�N��H!9b�` ��PK�Խ.{Im����"���P(��`��J����i矚K���D�9��X��� bH�B�>&��^��v��������� #7u���g̀(l=�r}
 �3͘����G̛U�H��,��[��Ӄ*�@>CJ���H:�Ƙ`�=�����PQ&���A%+���f�4�����ֹ��֩4�������s�'3e�;�޳�t�f'9��Y�@�,�r��("�%5��{��(�fpP�>    '$#~����e� �׊����s7<K��ĪfĂ8$���B�ݛ�lV��Tx�ZltHz��a�����8�l���	����aLHn����y�T�7�⩝�{VA�ͮ �ܞ�p�JQ���_�����keQ*g%V+~�`�����-�Vr����^���N&��9�`-��:�QF��d�&��{
���h�����t�u K��W��i0&���r��Vq,ˡ����B4]�*�p�cN�J��6�v��H?I15Bj��{��1�ÔY�.[MU�>�>� !fUb�<�2�	�����3�[�����Rf�@���=`���5�X����27����l�#�����{V�&�^��-���=��-�(���=3CU�֤�ӷY.�/�԰JO��ވH�0(��
?5Ψ���Yu&��� ��L���l�Z7^b����.��`�[�)N��I�ܭ5�nOu�(eo�8/A�[a���>�"aHc�[�w$	"�,,���Il��Z��"^���&�cRD���{cLv!0�f%W{��9�A]g[Won��z�f0u��@�7�o�*��r�Ny5�tV��Ol��6��J���zJ��Y��&�$*���a|�f(���u���c�T��)+��B!����P�ڭK�wZ��iM��dE���~�$r��d�¬�Ο��D ��/&jiI��(ڤ2Sf����D���܋	Y��Yh0#�g
#.�F3]��2��!;�[A-�.y¯�&v�e	Z0���^���tq�1�0�AS�PI!q���lò�g��l)���O�LM�O?��ϝ��[����*7�����Nt����-!\�"'�G����� ��-�����z�lMk�P�㌛���!�l��_; t���.8�Yɫ8|
$��q��6�!�F*qgH`����L:�$A��;y�'gWm`���r�)0C~�1u�-�2���C/����CI���{�r^�A��AO���&(Q	ɂ~�
1�R0�-!U)���� .K��)_*���·c���"�w#��^����;��<i��I�Dj���[&�K�� �ەwZ^WRg/X�u���5�����pF��p�A�4&�0q��J��������{�f������1�p�eG��.�M~�W��-��S�B_p�a���.��B�J;�h�:N�c\�e����8]�t���	�훮�i�N�g�p�:�o#S���k�J�p;MMu0��J����4-jڹm^�&�j�9��8M�R�޴f�`dzx���%�����K�M����l�H�ͳ�,-枳��^劫�:Y�����,��6��ć���)
;�#���Y��Cn[��č�
��=��rN�m�Y'��kU�R�x���������G���sH`q�����䭽�aP�0q�s���w�X,Y돚�� �*�z��Ew�g)�%N9�(s�^MǾ0��!��`b��YI�)�4�%B4�9�q�a���j�`wK��35�P�P˜#���l��8�c|�A��.�DϢ`�@���9�s�
n�r�e�D�rx�Cw�b���1=�\�&�m��м�����������3�I���B�TO��3bVEN����gLR�){�mD�_mc�@A����ڧ�����g�o0�~����0<]ӡ�}��:�6(;� �NgLi}��c~2J�P�9��J�0����3pFY	�7\@�<��I׷�����xw?�ꟁ���L��%�[�����<�|�����bD�K�aa��7E	������R�(�H�1 ��ZЉth����p�pݪؽ�7�П��$��I��$q7N)��9ܚ�S�M[�5<��#T%z�5Cr��XJ)���Vj�a�����tĠx�U;���Xx�1�gul~2ʹ��Å��r���X$�D�®�l��ʌA�]�X(��X� ��.�Q�Ǩ=������Xȣ����Bgg�`�Y�X�łV����łc����1�y���#6�>��\,</����.@�)�_�s���ł }���Ij��\,`�b���0S 쬋�cj�<��yᦋȡwO ����	�J��p�`����/\,�CI�X��X��d�.],�P<8�w��9b�ł�6�XȄq���2�ba�;��C)�����ߧ.t���V�h�Ȑ�߼zY֎�.6�z&(���[ǭ��u�� ��\,,X��ؒar���c��	)1��[.^/�\,���30)���"fo?r��zf�+�'.������-;0�O�X�(	n�bA����h��H�V�薨��nx�P�aHKM�vo�>򱀱,ʲ�W!��nrC�*}��G�7��9	��+[4>����z�8�F�cś��ǂ�����;E����}�H@2w�%\,�Y�жQ~�Cq����nߠ������="��r{����Bq��[Ug8-קj3h],���.�k���J�Zg�cCb_�b(�f�e�]�>�������K~2�&�#"C�=����v����Y��,�_${k�����)�A��C-�������|m����c'˒���,`�o7���v� ���{�d�l��(H	7�2�\\�Ű7s8Y���'��o	K��c�1��B8Y�ˎ�6���f|����M�Nh׵�r��ɂ��d��3���{����*�࿣��<�'��G�[���{ڿ�~�_�#8JI�~o�>��P�[B�[J�����a��Ľ�𯰸ܹU+��6�l�g���[���ڿB��$����,�'��� `�Ⱥ� �w, 5<g"S��2� w򑯋��~b��!�"u��p�.�������-8��b����est�6wlaʀI�8�s�BPSA��x�g"<F���u�G��ϒ�>�p��0&�A��<lI�\>�O�t;��Z@��Y	~�{V^$����jh�Qרy��s(�7����	� �w1�fwz��s(�>C!��?����@`����y
���)��+K���������LWjQ���V�p�����_�b���������9����@
��쨄�&�#�$��\�f�� ���y�c�9�􀿠�G��VS���0āw��"�?���G,e���cE��O��nw��Э��9$���XR��P5�֑�rfN�h�؀A�^#5T`��t;��#�0����Ԇ�+E�p�2�BSoMc��m;�1�D��!8��������l��"�Fl���9́bB�Z�*������I���v�x1o<W���v�_��JL�)���1xs����>�9�����`�� �'���ԇ�p��-�sc����QU�-��얷Iͯ 6O �StXa�!�]��G�&�u�A��	B��������J���B��BeՈ���0n`�19���!D���H��ܒ���������	A(v���--�8^���cՙboKW��&Gh�'G�ťQ
���q_�;��*���ǡ@1�"H ��k=o.v��x�v�UO��F�k/I$%W$Q�.aS��UM��&�w|�V �fIC��@�e	�����N��1��X�m�
z�{�Y��y	(S��
-�DݠP����JSxNݳI��w�׻K"���Q�~�ID�M�
�����"���;�K 	Lf�O5�NjY�+��a���\]��ۻ�
���CV��=��脽˓z��2n�V��a�
�>"Vt�@AM�4�x�u' ��p+�����H�G�0{~J���U��+��L�i�0�x�g wD��t D��B���k�r�0���p�����5<�����7�SDW�?-x�>j8���c�B ��eY��3}Ȳ�C�5{-(��CyaX��nQ��ob�;��� �	�¢��?PY����Ԯ8b*��A��#r��T*�py^>5��F�ᦘh�D��Yќ��p���gU��wh�D,    ��f���c
BmY�Q*��0ǩ�|h9vP;�a�-�B C�%T����� �Pa��X�p]�MXG�tɦ���)�"ø����L��0@θ�R��1���?1��#�sB�.y��B�<
c�'��S�H^X�$QЕt��x�+%JX�`�m�D�Y�7������ˇ���3�d_�N�����ηk�	�<���D�^!N���7��l{�uo�G�ȫ��-�.q�Y���K��d�dZn�<w(E�0�Lp{����ߖ.!�%��K0���]����b���$���׃�
-'Z)�ku�-a���J��Y���)!�E�R%"K�T�f���t�&!X-�O�]<�yt������I����1K�#5��$�h�E���	��Q$Bh��ۀ�#��qS���3P/x�Ģ����s=l�#�Ff~�Q��cr�|�,1�WH�aF��{f�
:���
�r�Gj�B0�<-��Bf1�*�/� ���(&�p"��_���۞O����ɠX���!B�#BD��U�a��[|�f&�FͰ!JP�c6Dn8�J���N�9�q�0Ʉ@M�!�9����,9����fA7;�mӾAw)��#�/A4���� �R*��뺁�����4����M���ĺ*��� Px��/�!�ήM���ER��a��P#�lY�P�u��;�z�v��]r4�}�y �g���Z�*OH��4�w�!r�w l#c]W>e;X-o� 8Zُf�W��U��@��_3���n�p�E )j���,Va��g!����Z@��ލ� {Jr�E/5�h>Ȯ��&u�B���%�F$�
�w!Px���i!�"���Z�d7^PĻ>#lL[*��Z`�8�\Xl��+:��H��#@u�MO�ۇq-��[>� uIk����p�#@�ZdL�x�(��vQ����(ҧ&
�{��� ��$�-���[��^CH�RJ�1��	��޿݂1�� ��l��ѧMtL�e�u�s߱�@UOr���2D���-��ķ@�n�\YB=	p�����%,/E����$�Ų�p! E�-B\�.ą 
��#ƅ ������ ��>�s�w	 ����S ���a.�yc��Z��G,�$x%7�?��gz�8�Hޛ�P��~��xs��QDN-�"��#]����! #�p�r�Yx�4��p�/�*@�����X���XP�l��,�|hƩݥ�F�p�#'�D-킜7�.����<��,f:�Eڻ��f��,x ��ƻ(�˓x:��ʎ&&���x�0�^�M����e)��Y���1�䣲�GI�T�D�t�����R�݅���f�1��ǿQ̋M�B K���0&
��Ի7H
�\�[ɝe�Vډ#P�.4���^d�PR���GG�PK�A���eW������S�Mj\�������$�}��}�P�
��5p�ڡK�{��}�Q8vn��'�56f�EW���f� LO<V�	 g<�6�Z��(z�m��/���
�mג%;ʣ���K�1o�D� �ݣ>�����$$�a"M2"����CH�$���
I"V���О�s�w�~�X�'�A��0](��t��Pv��h�GJ�=^a%f�P�c�A��fV�h��Ő�b�|����ĸG��}(��	f!?L���%��2�jA�}��'�y��J�X�9�;��ƛ>����t�~��D�mȅ#^��TH���j�����x!��j��ǫ�����b1l�)�2C�j�/TňP� �ڜ��bU� #�r��W�e�,9��}����C��m��(h���x�;�1� �(��g�#-�/�NT�@��U���chg��I<�r�F�}��@y5C�,]�I-:��c@F��+eLZ�~w.�)+6����j�	W�s�{B�� a	;����/���3�2����t�Ud�d�8���A�X9��sL&�O��oH�r('��~j������*1U���:�w����6Y%��b����N��}���t�c=����qW�g{:�o0�M���P���`�] wu�l&®b a�1$�M��ַ���;��'n"�-%�����O�N'cQw2����֓�)�m���O�m�����7�ĳ����o�J�|P��l-շ�ē����Scܱ
qPX�%*;g�Ҝu������4�~9lv�v?-�}@�$����l~:����G>�+��"/�|0V6*��U�u��v㇔dӊ��$��N�(��
u�9~�s�C�3������`걈x�?�d��qڹ����-p��]7Kf����у���?Χ'}PyO�]1�
����+4�@�����{ܚy��CU�~���
|�f*��iml��}��<çbʸ���aѬJ���e���A���<ߨ�	x�����h�i�Ў�/s���\�%�g�mM�=Mkh�BVԿ��� ����z����іc��y"\�{�x�I�=��7l!�,�qc��]3�i��Q���tx�o) ��K���T�C�S��S1>����,�������{m�$�s؉u9R�}�_����h�Ki9ߦn��`[�u�@�0�H���w�V�3�U�Z����e��h�^M��cr'&Y�姭�,���ԍ^���\+�[�^�]t��l5�� ������9߇dE����4�eC���;����v�� �CN�:��7Ѳ��y�rp7e�J�!y
�Q\�=e�@#������7o����#�)�xH��T |�MSI�ǖ?���g��-���,��:��zp��oT���i��v�ٴ���Ej��լ�7��66�o�2K񉶽'��W,2�-�p�96�<ӛcq�:��w��<{�po"�~*�GU<�{B�%��п����P�!�ޓ��t0m ������]0=��=%y�C�f��`���w�g_�ܶ�QV�KA�k��^�sv.��0:�ɶ��/\KE,o��ַ������s-}O��ֱ�"8���i�A�T����Sc��J���Iw���R�ۜ6���ޫ��@Z���|�͇�J���C_c\�	�sN���M��=i	a՟Tp�="��z�������A|�'�mU���Nmv�z>����(>�.n�?�I�E�Ap��)ܨ���Q?�}��y�'@u���\�͹å�"�\4��I��Z=����/��<>�JA�SPV�tJ0"�D�~Q�a�@2y�C ��'T��fDRG�r�LȔ��q�?� �wOzO�Q��#)��A�lx��.?#�r�C^�OzO�U莋#)ۇ꾱]�g�~�b0��D��q'�'lA g�\g���~��0�}y�&
<�(��d�G� ��Ec��
.�&|Z3��{:��1C�z
���m�qIS2�� 6"��]�k��1C���=���C!Z�=�|��XmS����0܁*��oc&��,e�V�18�}�Z�m�d�����9��� �u�p��>���aM���{*��{\Q�V�^"�]��\H����h��k
�as�Q
���Q�_�>��1��z ���JN�P�a4��#� cJ���N�G��tgf:�� %�k�,~�ȱ`)Y��{_�A�������(���+�z�J^D�-D��dt`�0Hm����L��f��q�
۶��'��G�����7E��O5�a����0���#D3l�[��h�Q�?*W�oWU�D	(5���w4ۯ�����׏��9"��W�yO	�U|������V� ��<M�p���&"���n�ѯr�L���eVx%!��p�Px־�(W��r�J�(�Zg��1P�n��e�(�����EK9.�9�bڷ-8�+�w''��<���b)��C��BȨ�?�������\�UϘ"��Ǽ��^p ��1��G[p�'.Ӳ��1��-u��a�t�yǹw+�[�~��^�2��pM��/��*y�l���w0)�
��A�G���.�e�~ w�m��Ɉ��t����
$�	�Q����Ď1���O���4 ,  �}܄�F�sn'�P��?S���g�����$�~��_?,��*{6P.ȏ[�=��V�s�X��n7�9h�mK�M>a�taq��j���m�@������q���$+z��@�pH�^�<��r�7�����B�����bp ���"|��X�wA~|F�BE�A	p<B�j�U���3�=�2"B͍�k�J�jbQ�	G���pA{<����P��u��]���\�Ւ7Q/h�)��薶���p�����҆�v��>�@��R�~��FW̊j���|A{\������b�h(�W��M�%�����~���� �D�n{����M?s��Ah�(���u�ϳ�g�S O�C$(�]r�oǨ���@��Ep�Q���X��58�����?�����)5�uX�_wN��*qf	�`AkG��!좉�3��ݨ�c�*[����i:|=�o��.@<xu��g��2~��%���n��xhw���u󢗇YW���9��7S�>#�.D,?|���	�,��ঃZ+wh>��
�]K6R�?�<�m�'�~��%-PB�=�o�p����Z�Wb.�HSXB8�٥�C�\�����������E�H����)!�q���h~�F
�Du_ ]�.{
q)
j�ނ�aL�Cq��2������5lj}��" �q8:���1��H#nE� ��ͱ����i~DM���α�"���gR�i��}`�:x]s�z^�HX�P`�D8k�\j�4�w�2x�P��,I��<�砰����F{<b-��"�"�+�� ����R�8:�C�V����x�Z�S�t �6�f>#0���E.J&˞c�al�Y���趧ps���q;Y��aSd9��`��H� ���r:�����&��ZW�l�K���� �+J��2��/��|`ToSs�SN�s8��9����ǡ��V����4b#���h��|�ĳNp y]��Ċlt��0�u��R�M�a� �_�=(� �Yn�&]��( i�K�6i@����������<lO&�*H蟩���(�[�U��GׂŦ���	����T�� y<E���6>��hp5�6KK�U/	�7�	����ͫ(r���`�3Ӭu��h�����o��T�[2�4bƦ��(���%�%�HW�Ñ�J��N� ���䟚5��.l�g���A����|�>d�͖�&��5hI �*�~���{��5]6\i��F��*
Ţ�Ņ��eг��7��!���4`2IE�7ڿ��S!>�u�)�����h-�ڈ_��>u��Ѧ���f�n��%nAڸ"�2�����F�Z�	�5���Z�?��C�P���*����U]���^��:��@2P�0̠�%uZLK9O�VxS�#?��t����`�s`��2����u���a2*�&�H��{pձ1#��?!��u0!M �"�@Nu�E��Ak�5o,�����oILK���t�ܥX��]?�]�Ar�ݖ;\m/�y	2h7�B~U�C���j_W�[�=<���}pi�������2�Oa&A�H�>p������fr[���B��>�lP���i���v�/؉�5�����ۘꦻ�6���*t��]��Qh�&���� T���s7^2�RV��$�t0%�q��F{��8t��������J��+�B/"V�>jc�*�[ �k	LKn,���t hr{��Ъ����ǿ��@��UD��yC=+��)�J�8>�d���dk#���1m$/|	�I�	�:�S���Y9|՟xu��T�ետa����ݔ������v�spە����/xX�����5��>�e��X}=z:��-$KE?lĿ|���$�U���Li^	e�JU3;e���AQ\(L"�%�#��^-��x�� !B�D�lX�<��B>"��tFR@QmB���*�BrK�}x��6�":?��6��:� B3�(O>��9+�� Z�,u�H��-v�u���ud����0V0#�P����J���WȘ���khZ��7apH-r��	�`��v�N6�6������<�	d׵�9cL�	����%�j�c[}�A��i�TJ�%�ņ����+B~����.)gvg__q�Xb���-�3����>`?]$A�0�����e4���v�Sȓ�ޅ���FE�`�/�ꦓ���m��
,(b����٪Y�X� ���1�=k�<�+��(��[�`� �$��`_);�CY��`�iX����T�3J�~�F���9" v}�Dڝ�=S�/�q���d���}4�/0;IR����|��"N$Uq�o���
j�������A��$�<��8�H�J$%Sm��l�����s�p.}$#	��l�k�b.�!S{(�����P�?[����)ǻ��ǣ^�6�m�~KDX��k�ƣ���O�U����qy�&�PPœ�h����5|�v��^�oW��|O|���.�D��O�[��/�1�(�ܕ,5���6����DV�CV�mH#�-ĸ�.�+Tl�4w9v4�p�A7�V����plu>�ڷ6�xk�
���}Pt,��'��4Bb$9����*i�ݰ�����=���@N�̕�E�E��`3>K`:/	��aU0_;$��L�� %���ĴꧠmU�o�­BS('����>>�/� �b�xI�|��x�"� /�d�� �E���g��iK$s�=ǲs��EG���Օ~���7x�ٌ�Qlܺ�㯢���c��:��mZ�fna�.��[j����ɺ��Ų���e\�Ct��B����t�5��p�c���#��\o�}��obe-b�:3�YGA��37,4�db��/V�2]�:�g H�L��Ϩ�(S�I_EUֳܸY�b𬅮�&Їz�r�?��6���������H]�ϻ�M���-n�ᅭ���LD�e�[įAN�Z&{���J.�������V���gǆ.��[2�b0!�x��C��ڷ�p¯�UP-t����/���� ��o:2��D�K�#S��a��[�E�D�)w�k���I�⨐��CǼ%$/+P�w���)��.���WW�S]��v��&�JUX�i[����ꭖ����k0��\E:�u����+�G
�, u���������8�o�@���r!v�~=\��N/y�6P`�!-��O�;�f�͞>3e��O��' ���|����C���z�D0U��?��?������      R   �	  x��YKs7>S����݋����uR+Y.I�\|˳�T�T���o��GJ��g��nL���C_>.�ί��X�����W?���x�b���B!�Y��.�Y��J�_C���9�N!tNY��L|N���n�6I���e�%�%� ��(GU¤e-I��
×�.�&a�^m���r�^O�과����L���!-ז����D���vw���X16����t'�~77#�#�h����0��j��{f*�$T��oG�.�?|�<�Y�N�f�<7�ո�X6�rC��e��$,�i���z���wۚo�z�VÜ}�Ԛ����MZ�)z �/���8nVc�?��I־���&����e��Z^n�ל�//[1�'A�HV������a�_�����a���/�!�aZ�2�<N���f�.~Bs�>���ޠў|��l�&҆A��K_�r�}y��}��ⱴ鰰������A�@D&�_>��i���ļ�DC�_M c�״p�u�z%4=3.D���������@��Lɥ�� �K)X2�ݔ�$GQ�P������Y�cy�����fq=}[��[����vJUP�d��2��Ҩ����0�7����ZPfЍC�;zf4y���~�}\���~$�L+�M���P�\
0�Lӡ]z�6����j~�m3����V�|u}���������_��� #��p/Ѱ�G4*fQ���(k�|������/.a�O�7w�Z�����+����gh4Q�Ob�z�fxÂ"�Dމ�`�j3i�O���6w��*�h�m�*V=o�L��%���笖��G��P��p�Ş�6��1��5B�As�M̯6�-�>�#�xm��Vq%t�yM.��O�v��#�2��a>��v��
�褈:��8�֮�bT�N|f��u�=@��J&O*W�'��E�ƩT �\�9+�����ǯӮF[�nM�d�C�Ee"O�����Ъz��v�Ǣ���)h���&���)tNsZP%�P����0/�y���z�Z�ۍ�i�	� Sj�M����L/����n�=����]�Ƃ��'�T�,���U�E`,l���|�����0�Q([�ڍ�{T<f�g;{�6̀c�yV~
]L�| ���et��alP6���s�0�.a��ˋ�ݸ^T�\<d	 x�Kq�_��>�d���&7��s�� 0hm�^��ʩ8�n+���y�1�X=��+��(���L�vDl��4>=�����i���!�$���^��C�����O����� lM�[Ԥh��E����I���%3�~���V�*������d�[Ee"�%[�ϸn�2�l�кhY΄tp��� 1���9c������n���/��"�[N���Ҹf^��j*�������GB�OVN-v�]F�X�k�p�����U�}�t��t����&�]LP���L8��Lx�w�Sg��&)ی��ӥ�!%^��:%��u�C��Q�9�,�F,Kh��P�\��M�O�����H�C��y�c�0*��%"�){2t����%�^����q��
�$�S&H��$��!�6e��C1�+��19Ȟ�	����: �՝��?��H�X�k�vl��;4`ʧ�S����C഻�0v�x�y��Yn��d��r��P��K
Br^�M'ReY]�'�^�ixo�"�T|B�T�>,�8#�fbМ@C(��D�	e=V�d*���t��ֶ��T���w?�㪚���FD�U���V�i����*�*5
�N�w"]/�����"C+ӂ;�0�Z�Tr�^�lB�J���N�몐�<��kI��Χ��tA���R�H�@ΑV	uq�Ib��>^�?N�l���M|�(E���D�QC+���%��ٿf�>�1�M�s��z� �U����/~/\m
���:;%WⅻIB2x�9�X�-��@t�GJ��fJ�-<b����C9�^l�Scd�-��2�������+g����W(����@�H����BB�(l+	�i����5�(�u��U�Qe!��%��:��6 5���%����`
���:Q�s�VD���
�u�b��.��DvJYTLfL�"�:��4��\�S��G+|J���tsu
h�N����j��J�Bw��U0�)��Ϣ�0XVh�,���|m#6����#�����|�iL���'ypt�h��0p"��5�Y�2�0#���kД�\��>�E��ǊG}��k� QN�Րx_<c9� �� 
>���.	t�g
$q�J�Q%��W;*�z�)�뀣����!�#ߺ��8��b��[_E�`[X�Ld��`H:29�ԡ����p�Ij��,h���;�"���츃���8:kK,�9��8��B����LM��rdk����=�T��E�z��b�Q�%>���xt�K���;99��x�      Z   �  x�m�;r�0�k�0�x��3�ҤH������ǖ��7>������y��^K�-��	�����~.�RZK��& 0���;ର����;���@�{�����7�)� (� ���1Y�C��:`L�3yTS��tb�'�7Y���1 &�&o��[D��tM^���&q���$:QWc�\�*��+�1U0&ɽf�cZ�v�u�" kP��o��M�6����\����u.�-" �}`�I?VTA2�1����)�O��јU�:�~,����$:Q s'��@�Nf?&�;&��S��d�LFM��b�Ā��% v���d���o7����d��NT�	։� s'#��ɩ��w�U?VT���ESH&[�aݘ'�����?~��      Y   �  x�m�I�[1D��abM�p� 7Ȧd� ��?�r���?Pdq�.KY����������H�QR��~�jJ�.5b:"o"by�*Q."�W�T�^D��%�E��3��G<ˉ�v����8�fb�1�Q6q$��g���#i����I��1��%d:�0�C�hG��k�]cI��%��إ�� �JZ�#(i��K�bk��0a5�.� yH9��Z�Li�
�1#���<y�h��#��2�--�E,m�K�4�ť�֚f��+Zl7�&N4�t$��c1��%�c�C1�Z!�.�Z$t��)#pL%�2pLY��x"+a4�<�N��0�����g1P�5FS����`��a�����]�xII��A���B���Н�G��+}G��K}�6b��#���s?�#?�#vuUhK�M���4_�������y���i�kޓѳ:"o�a]{2�6O%*�����ԠV���x1��x1b�`��p!�u���� S$�FRy��s�y8B�����QJ�U��*戮İ�sl~�+J	sE�'�ڒ�!Q��k�}�\1�N���<����M�ҡ/��4Pb��y,�-!�uzN!��VM�uʁXJM''��R�hz��	G�Z�ꈺ�n3E�]���A�i��V�2��o��g~��+�;1��H���;�Z6�މ��%���<��ޖ�[c�^�%�1O���9�D��3���h�9Ftk�Kg�S�z���<���~�쾯eo�qOeP¸��9;"Cok�ֺ��}��{��3n�qO�u�^�ytc�7ʸ�W��+:�^��9$�l��e�Q�|�o"C_\�"
�	�ָ���7�+�6O��i�z��'�=���Z˄�����N��O�m`��4T�zX%4S렂���{��苐<�#��ؗ�X��|���j?������u      X   �   x�m�A
�0��9L��db<C�'��BB������d�~h��*�p[����,#�Q��p]^���J�"�B �/"��"Ad_(�܊���!�	�}Q ���!��V�h*S+�
4��4�����{0�n�q��Ԕ|qj�=����	4��h���ٶ��
�m=qj}����MEݓJ2�{B���S��V�mM����o�b����K�-��      O      x������ � �      L   �  x�U��n�0���O���Z$%٫� 	�C�f�r�U��$�Co����3�M�N���1C�:���V�E���,,*��?���C�~̑�U�t3
��eV�J�x��'}�[�ɧ�7�����6��lRK���g�3��ɇ>(k@�6��2R�|�Ȩ3;���cs���I]<+@j�8�9Lʗ oA�5ۂ��ו�A'ށ4KUb��XmY_WiN� ���)��}���82/w���QM5����إ�u����^z���n!L	��Cl���enG�v <�ӱvR�A�x�3�F�%�Us�i��0-7���pV��^���娖�� l��8i]���pWD	R�Y��SM�[������d��.J��>M��.��e���]'8�!=����U}� �̼�ˢ<�C���w��p׍���H����[r%H#>���~��Z��Bk      P      x������ � �      N      x���Y���E���%�I���2�q����D�G�JW֒h�j�����=��ɟ�?M�����������\�ڑĆY�s�#��U�Dp��_]�g��,�\�Bp��l��������n�k���=��5J>?��DZfs��NH��l�p�I�|��e�E�S��E�4��h4	i���1�M�{ˣ�H��6�M&���>�J*��K_UR�֡\�(�|K-�ު��-�Z6�|;_��z��Wy�:�Q6�|k��t����^:�e�����Y6���װs�ށ\��R��r�K�lpپ0��s�
�h���h�|HD���F�3�i�}n�P��N�T���'݈����ۤ��;
��L:�ir��:h=�����Vr����G,�\�������ȝ>�����?�Z�*��wPvڨ7�|#�M�|G{����|#r��y�%r�2��l>�K�.�Ղ�\�ryn�D.5:mB.5Mڨ	�Լ͇M.�8OI.���p���KmӎA���ܟ2o��|1�ւ9����N�D���ɉ�����̙����S����6�+�����ލ�:krn���&��҉���.-X��c�%r���+M.��[��ir)q�}n�B.e�4�&�}���1ڄ\��ϥɥ�I�kRɥ4�&��84�Tqhr��G�)VUr���>�'�\j����]ɥv�b���R�Ks6Sɥ�{���r��hդ��cm�\�k֤�� �h���̈l�OZ$z�]���%��|�r4�2�ѩw!:9t%z�]�z7��L�L��^�/Ly��v��<��<�&��|���8�$tL}��)s_���!��(����ٯ9C�����o3Y��/j�)l���1�=	iS���8is�h�NZ�$8�8iUݽ���VNZ�<��I�'�K
i3��{6qҺ��FS��s�SL%�ڜѧduφ�ʀU�����
d�^\�o��Ћ{�rB/��,�'��g��@Z���}2/�z�:���{_ 1��U��p�z�����ڜ�'�;����#�����S9�U<�����^�@ZG��d ���HZ�I�xZ#iU�������F��C������4?Y��՝�b>�duL�����)�-�Ȫ��Qz�ɪ:�=��jx����j������ћ!�"v�E�1�z��	^�߽j�XWҨ�i����[Y����Yi��;����Fw�}e%�:���je�>|d2�M&��|�J&�#�a��LVG2���l���2Y���eȪd�QY����p��e4��ՑҸ�NVGN�̫I!�;�a�dU���jOk\��
Y�y���ƷM!�#�����BV5;Y���fzM*Y���k�>�>�d�W�����d��xxl�������o��V��p�JSS-��Y�1Vk%<κ[�i�'�!�Cx��1"p�ˇ�_�M	_���{��Y�ϴ��|���Y�e�j�*�Y�0��ɪ�ِF'hdU��Ϗ�Y�{.�9�>dU�Uc��!�R��]�*�ށ�!����>dU����!����NV5̆4�Y=dU�U3�Y�e���UȪ:cU��j�p��˪�VÈ�/n�	_1��$��0�p\�RɄG�Q�i����R	ϻ2���e�Fև�:��Qw��h��)��8-�du�H�(Y�1�~���UYo>c�)Y1�|{��U7F*Y1�|JV{�칺#Ȫ,�FeY�1�*�����dU��ɪ�Ww��k�� ��#��8Y�1���Y�12���du�H�Q#YUg>������d5xo�HV{�b5��8���Jc"\g�Ve2��+���lλH5V���SEc#|}��I�+a�Bx�-c�=)��i��Y���DVG�ڕ!�#a~�j"��YMdU���UINV{��2���,�'���-�ɪxV3YU�j&�=���8YUu�G&��rKNVG����LVG�R:YՕ14��Ց��+SȪ:�%-dU�W:YN�P1��f���0��f���T�-��/Y_{٬�WP�����P�-��rg����4W�.��6�펹��n��JVe��Z��UQ'���	�dU�Uv�du/�Y8Yg=VY�j#�c���n:~�5�:��yd#�{�U��6���*�6�:���^mdu,�}���l��ձpwi�:�N�Uzx���;a�=<d���n8Y�G;,��������q���0����3��-�y�#Z8`n)���n����<x|��O��o�'�%1�����Lͱ`r)�C�ҕp�`r)�cbNS&��<��i!�"NZE��'���Z8i�ub��ɪxw���:�sθ%���Q�*�iH%���`�p��kl[�!���a!JVU�G%���U:Y�������&d�PB ���vdU��#Y��js4��#�q��Ke�`��`r��|����b�\*3_���K"�QL.�y��\*�س��\*��������v'�dU��u��du~�����������>�l՝����v�'�:N@��2Y�r?Y���K�Idu���dU'�=~�ۢB"���_"p"�s�p��9U�H{H�w-��Y��t�-f陜꺞���i���*CNu^Q`�z&�=�eo��������ZZ�R�g������:�0��wSXg������Һ��\��ZZ7T$S����SK�-SKµU����NVG��T���pm�dU�W�:µy�F�du�k��JV%;u�dU�W:Y���dUڮ�qxT��'�z<*Y��1��HPɪ���8Y��d�duL�������+������0g�eY�f��	�=<Yf�7��]�sː� ���V̯�x,3`j����j�Fx�VO�GL-�9�����8bj����9V#��ڜ__�@xُz�#�u?�O���h��ZZ7Y\*CV��>'�;'m՝���,�+;����,�҅�z���U�^�du�-���s�E�*ͫY�ǫYݗZX8Y݇I����}��g���}����JV��Fݕ�jv�dU=�JV�:�dU�W:Y��.�o��z��Cfi�oa���%����^��Y�7\dkg\��Ҿ���My��Y�w\��@�:G`�d��O��]�3��C�vdu�˰p���tY��5_$��t�yN#YݧC�H��8r�	��Jٕ9�NV��p�D�*mW�4A�����t�HVճ�jߗa�,b$�}W�	'2��d�0��;2l�l��6L.�n&�s?��ǹ�Ƃ���Wc��p�9��":.�{79ǿ���L����MG��G'��K��g:#�f��U��E�IWk�cp�ţ�ߺ��EԸ����nN�� \����۱ht)��S�I�.E'm�]J�j�.�s�G�)'E�����eO��m�g]��З��]�=�͚t�O�^�N@��x4�To\t����R�G�K�N?	�R=�]j��F��ˀ.���7�"�To\Ft<�]q�2n�mFd;jƈ��٪wB:816f���ib�bAzEd�)+Ҟ�ؐ.���S������F�I�n����1%zD��s�����|K%t�F����zJt�F����*]J�ht�F�t�'	]��F�=�s��]��͍F�}��3������G]�9]�.��.Uw�g]j��o����2G��OF��dZ4�Լ˖��hSˮ�k^P�v��lujs���]�3�t���������I[e'��Gg����O)H�Iwʖ�t��1�KC�8t}��N�� �<Z��g�౟Tt)2�6\Vt)˥���R�Wot)q��8*����~QUt)�1_�eOտeO�ХT��.{��Ok�4��ǋo5A��ƥU6�T��.�0[�e���ݐ�H�P�k�r��6��s�'ʧ4Njh�E�ed�mj��q���6��V�h��l��Z����w��K��*?	i��t�Z� �zW���b�ݐ�����,Z��|J����&;����\Oy�y'������F��t)�=%�����R�loå�KI�yA�.O!"+��ⴉ�˾�Ԧ�eO!��F�=F�ht��2�q��00�tɊ65xuA�=*�o2~��fʽ���8+��1�R��6GL�����̀6{L��hs���m*DY�w��߮��C8���N���5{�����?�n���jR�N�S6���`���?�b�{���O�Ws�m�d�    ���NM������'��f��"�a�k��wf�hs�Y�k�6G���
ڔ䕍6w�5�Nh��i�6'����	hSڦ�z�q�v�P戳v&�9��lt��kAt�ѣѥz#3�K�\ft��;3�K]#Әdt�Fٌ.�(��e�\�Ph:tF�3?�?m\����/+{��i�F�g����3Z�.�H[FB4�=�?m\������ٟ6�ͼ�IA�oH��]�!������w]J�Oi��R<�]Jv���K)NM*���.v*���B��F��F�m]��61�IE��]ڱ��K�)���e_-	Ѧ�eO�^ڻ�K��.�8�n�R��&]j�j�.��IC�A��ݶ�>K��oio��tp�NkH��h(σt�hAz�7~�Q���	H/��Ry"�����=������l�it)�����M;�A�;"[m�.��\]��lƪ"�rD�d�t9"����.GD�r�g]��|)]�l��.U<]���Ӹg]�|޶R]��Rѥ:oע�R�K�7�Хǎ���/eo�a�A�iф��f���R�q�ۢ+�ɣ�^{��2i#VAz�L���7vB Z��L	�r�A�&�r�L���K7t)ѫ7�t�`@����Dt)�WF��R�S��.w�ht9����r�A�lt��ˈ.G��厃�.w<gKD����L�R�wZB�#f�F�ڜX��嘙��$��13���e��l���z�.H{9U��3vRC:9}0?Hg��?��;Y�vr%����#���f2���*]z������V��rGd�F�;"tA�^��t)��j�`A�R=]�7��tg�]�3ӂ.��]�7.�ܹ�F�����=3�ht9���L��ˑ��6�.G��Rot9��z�L3{�3�+k�镉��۝��x����"}��O��d�G��x��O���2
$~�\$�>xδ"]禈x�S ��*Yߛs�#и���h�/��-8�ξJַsׯKC���䨿�OY��t��OY���t}���"��?=g}Ч�v�S#�}�-b�1�4ꔶ�>����νE��	�u�-b��?���M��~r�Y�A���n��B�s�hs)�����MM��]�
��O�V�u��{�
�T�y�Q�v�f��
�Ύ�*�3�t�X��ɰoתIAz�<��*��ط{���io��dطkт�2o��?y�۵��?�M�c��EG����@���wO�؃m��Rd�伷�*��#.5A��x��]���H���<e@��x��Ӽt9�Gd�|@��xĥ�t��GX4��#����r�0it������uԀ.��K���R�K�M"������.���F��\Oѥ�cK�.�8���eG�nOY��}��LW��<�e���O�C�F�!�S��K�!�S���h��O�#h���n���G�.�;�K�lt�_FZB���ѥ�M�{jF�� ?���m�c����5�M:|�Q�8ln֯u�s��	�s�Q���F�g�9��G�jF���̨s�6tfԩq�ɑ.h������oժe��㌽�K��>�]*�s��r���Z�eX.����uS�G�ۤ"v�<�ݐ����zC
hݏ�~>ӂ4��>�R@�R@|+��&i�lz�к9�R��4<��e�xG��\�Q+�����2��1�n(s�F���[���`�a�i��g:!�@	����B����"V�+��%�?�g�A���P;פA�ge�ۻA���<¢���V3._h���jV��R��ۡ=�re7ͷ]��엲�%e?�������f�-�Rڦ�e���j����4�쓞K�t���]��VMХF�&�r�d�ht��KA��[�����Վv]js�Vt�^.�9�K�޹�u�ݞ���Y��{MN�޶s?t�ݪwF:ytA:�z��t�e��4��C���>�n�����s�>Хx.����o4������Q�����s���}�ݪ	��e@���E��q�]M:�˱Hl]�E��.�"ť&�/RXW���&�I�E��,Z�tg�a_�Y4���l��`�Mh����,����-���;+O?���r�7�~��Ƶ0?>��?���D|p�����kj~|!>M����+���ظ���7�W��~V�뮏��_v��8=����u�����c���Wd�F}ȯ���3�O~%��5�'�g�f��_In}ȯd�yɯ,�V����_ֳO�x�+ͫ$����/$��	�5��ѡ#	V�����V�$X� 6'F?�k���@$x�n�?���Ip����Hp_0�=o"�}y��	��A���?�B®O"~��g�'?�9��T����sK�����rN������u����3�aF�������?> ��Cl��c�;D&�2׷�7F&�� �|��El����W�~<��+#!���+e��)����o_	�/_�x�+3���?�_����W���F���V�^����E�K{�sӸ��Ǔ߾Pr������B~�b���a��J~{�7D��J~��ɭ�����e<V�;Mn��~G��ޟk"^'_��V3�a���o-���=nt��A�?Hn�6�`���� 6>=�_� ;�a�߼ ����������j6;P#�;@����O�%x��`�+�=_p�,�l6(	��4�<$xEhk��C������൘���+	�%ج?	V�b�A����2l�hy��Z0�y2��-�k�=�8%����X��-"$x-�Y�� ��I�ZƓ�W'�@��:�1R�Ƽ���mvQ���Y�9� H��J�7)i�'�����'*�gR ��W��Bk\8c�q���"��@�8�%���y(^[�LJ����l!2,�-��i��֐@�e}VY# �a)ۀ@��� �ge�ﰞe����<f)Vw2������a���%���M��G2�޼L"VwG�'�f��wM�͘�ox\��x�u�Lָ:p��s�Lָ<�y�J|��d���s��K@ �5��� �5n��He�{�RY�&�8Rq��<���]����������? ��Gd��I�ڰe��)��)3��L4�a�y��L��x}.����
�b���1�I����̓���! ��=�.��/�a���y�e2�� �dX�נ��;�	ֲ����`��?($����,$xϼ�������)�͛�>+���݃N{V��ǶY~#~eˬ���Ym�@x���Ym�Ax���lV���뿲Ym�Cxo���j�&�/;e���j�.��K��ߑLI���ʖ��K~%��I~%z�o�W�l�4�+s�͓�=��|5������������=�n���4��m6(	���I�W 	V�ǀ�	v�����=�6y�κ�!���3��ч���}��@��߱��N_�C~�i�+~�,���P��������6��f]{�D�!>���!������<�c������H�<.k��浧d�e��'��Ь��B~�V�K��;6cG�|%�c;v��W�;6d��g��ߵ%ی'J~���1x�+�_%����)S%�co��iU<vg�*D��O��w�����آm�dX�� �'��H�������fD	dX�ף��/"��0#���!��un���g�����*�A܆��S��B��������(
�e
6�W�篾Y�b����_�#�2w̛(�`qG�~�Ѯ	���O~��戏�w��� H�W�˓_�O~��|�;�ė)A"�cJ|y�&��|Si"��ޔ/��1%���D~ǔ�Ɠߵ�h�L~5���_y��*�)ϯ�L~��O��'�f�w�X�%�_�v�O~�/{\ސ�ƙ�ݚ���$瀛+�Ap��47�� �!>yP�������-Av��������o�y���J�?��E� ����B��=���}Ӂɓ�}ׁ�W<���K�:\%�+�a�O�GD�t�J�GD7����Wa��9bU����5�J~1�aL�*	i�l��J���H��������q��Gn�xݴg>A#��->i#�c���'��qyP��A�ۤ�Xi���_�����&5��J� ��Bǘ0���I (  �� ǗF�DV��4�Ld%8Ic<p�DV�'i�g�q��d�q��g'`&+��4�����~��� d���=���R���\[�K�*$dX�˓�~��ʓ�~��T��a�n�$����|�!�:��`�=d���4W���<+e\���ɯ�ݡϋ�AI�.��.��$x����0%�Z�<	V|)J��YK��`7F%���ͯ�	��<Y�Y���?`*+��H�����uP�d�y8��0�����b���d�ү��[�H|��yCG�TV�!��d�GH�= 	!=���H0�t�|,��O$���{�C$�❞�>�S#�#�=�#!��������n> �~��e�E2��t���H�{�j����k�~c�^Cn"�#3k�e���7��1x����;5��~�ڭ��׃]^����ʊ�A1���z���ˀ��~ۣS�F|���\V���ޞ���w>�m�P�\V��Qo�c����:3�퉹�~�ގ�e���퉹�~��Ó_���\g�����O~%��b�^��B�%zTH�$�,y?���	��O����K���_I��mE�B�W��y2�G�B�5�x�+	^�l�J�w��T�#�eu(T�&�f��$�O�/�I��`� {��7<�$��߰>�M�VJ��'��ʪ�H1֧���$�����
��}0��7�XY�F5ϗLd�I���U~"���d�Ld�I¥>�q#I��O~Ǎ$���︑$�||��Ȣ�x�;n$���;n$�f�Ǉ��I��ǯ���ߑE�����!��F����+>�W����_��Ƈ����3���ߑEI���{�a��ܞB~{�-���/?��j��_�oO�����7?��j��7����x�������x?�!��B�O~�ڑk$E"f��\�L�/Lb��z����$V�����c����d�OLb��x�����I�6/o�Oė�ǌ"&��\�̗�,�7�y+�c����hO�+�O ����s��+a�F��w�]f;^�;vh�K}��X�,��%�Rv�Ͼ��k�O~���>�w�]�ڇ����b&�c$���?��3�_u�o$���ɯ��7�߱��>����(7��juy�;v��x�;v������H��2X�>�|����O�`��H�y�@�־�ĞG�`��Hn�Oė�>F��������ͭOE^���o&�#>_�O&�\��b|>��L~���ɯ��7�_)n�ɯ�ݞ���ɯ4/^e�7^2��9��B~{|6������|��m������|��k������|��i����jsh����&��G�����?���?r�     