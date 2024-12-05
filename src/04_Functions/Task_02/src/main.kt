/* Биграммный шифр Порты
 *
 * Создать программу для шифровки и расшифровки сообщений.
 * Программа запрашивает исходное сообщение, вспомогательный символ и спрашивает -
 * использовать типовую таблицу или генерировать случайную.
 * При генерации случайной таблицы каждой паре букв сопоставляется случайное число от 1 до 999.
 * Числа в таблице не повторяются.
 * Каждое число состоит из трех знаков.
 * Если число меньше 100, то оно дописывается до трех знаков путем добавления нулей перед числом.
 *
 * В результате работы на консоль выводится исходное сообщение разбитое по парам букв с пробелами между парами,
 * зашифрованное сообщение состоящее из цифр разбитых в группы по три цифры и пробела между группами
 * и шифровальная таблица.
 * Группа цифр из зашифрованного сообщения пишется под соответствующей парой символов исходного сообщения.
 */

fun main() {
    try {
        // Input
        print("Enter a text to encrypt: ")
        var txt = Crypto.Text(readln())
        if (txt.isEmpty) throw Exception("Message must not be empty")
        print("Enter a supplementary character (must use the same alphabet as a word to encrypt), " +
                "leaving blank makes it a random supplement: ")
        val supply = readln()
        val supplement = if (supply.isEmpty()) null else supply[0]
        print("Enter the encoding table type you want to use (0 - Standard, 1 - Randomized): ")
        val keys: Iterable<Char>?
        val valRange: IntRange?
        val cipher: Porta<Iterable<Char>>
        val tableChoice = readln()
        if (tableChoice == "0" || tableChoice == "1") {
            valRange = 1..999
            if (txt.isEnLet || txt.isRuLet) {
                keys = if (txt.hasEn) Crypto.letEnLow else Crypto.letRuLowClip
                cipher = Porta(keys, supplement?.lowercaseChar(), valRange, tableChoice == "1")
                txt = Crypto.Text(txt.value.lowercase())
            }
            else {
                println(Console.format("Provided message cannot be fully encoded with " +
                        "any of the standard tables because of their restrictions: " +
                        "using the limited character set suitable to encode single words only consisting solely of " +
                        "English or Russian (omitting 'ё' and 'й' symbols) lowercase letters. " +
                        "Therefore, the best matching table will be applied via auto detection.",
                    Console.Mode.Warning, Console.Style.Emphasized)
                )
                cipher = Porta(txt.value, supplement, tableChoice == "1")
            }
        }
        else {
            cipher = Porta(txt.matchCharSet(), supplement)
        }
        println()

        // Output
        val encrypted = cipher.encrypt(txt.value)
        cipher.printResult(txt.value, encrypted)
        val decrypted = cipher.decrypt(encrypted)
        cipher.printResult(decrypted)
        println()
        cipher.printCipherTable()
    } catch (exc: Exception) {
        println(Console.format("Invalid input: ", mode = Console.Mode.Error) + exc.message)
    }
}

/* Sample I/O
=== Input 1 ===
Enter a text to encrypt: АБРАМОВ
Enter a supplementary character (must use the same alphabet as a word to encrypt), leaving blank makes it a random supplement: Я
Enter the encoding table type you want to use (0 - Standard, 1 - Randomized): 0
=== Output 1 ===
Plaintext	аб	ра	мо	в+я
Ciphertext	002	466	355	093
Decrypted	абрамовя

	а	б	в	г	д	е	ж	з	и	к	л	м	н	о	п	р	с	т	у	ф	х	ц	ч	ш	щ	ъ	ы	ь	э	ю	я
а	001	002	003	004	005	006	007	008	009	010	011	012	013	014	015	016	017	018	019	020	021	022	023	024	025	026	027	028	029	030	031
б	032	033	034	035	036	037	038	039	040	041	042	043	044	045	046	047	048	049	050	051	052	053	054	055	056	057	058	059	060	061	062
в	063	064	065	066	067	068	069	070	071	072	073	074	075	076	077	078	079	080	081	082	083	084	085	086	087	088	089	090	091	092	093
г	094	095	096	097	098	099	100	101	102	103	104	105	106	107	108	109	110	111	112	113	114	115	116	117	118	119	120	121	122	123	124
д	125	126	127	128	129	130	131	132	133	134	135	136	137	138	139	140	141	142	143	144	145	146	147	148	149	150	151	152	153	154	155
е	156	157	158	159	160	161	162	163	164	165	166	167	168	169	170	171	172	173	174	175	176	177	178	179	180	181	182	183	184	185	186
ж	187	188	189	190	191	192	193	194	195	196	197	198	199	200	201	202	203	204	205	206	207	208	209	210	211	212	213	214	215	216	217
з	218	219	220	221	222	223	224	225	226	227	228	229	230	231	232	233	234	235	236	237	238	239	240	241	242	243	244	245	246	247	248
и	249	250	251	252	253	254	255	256	257	258	259	260	261	262	263	264	265	266	267	268	269	270	271	272	273	274	275	276	277	278	279
к	280	281	282	283	284	285	286	287	288	289	290	291	292	293	294	295	296	297	298	299	300	301	302	303	304	305	306	307	308	309	310
л	311	312	313	314	315	316	317	318	319	320	321	322	323	324	325	326	327	328	329	330	331	332	333	334	335	336	337	338	339	340	341
м	342	343	344	345	346	347	348	349	350	351	352	353	354	355	356	357	358	359	360	361	362	363	364	365	366	367	368	369	370	371	372
н	373	374	375	376	377	378	379	380	381	382	383	384	385	386	387	388	389	390	391	392	393	394	395	396	397	398	399	400	401	402	403
о	404	405	406	407	408	409	410	411	412	413	414	415	416	417	418	419	420	421	422	423	424	425	426	427	428	429	430	431	432	433	434
п	435	436	437	438	439	440	441	442	443	444	445	446	447	448	449	450	451	452	453	454	455	456	457	458	459	460	461	462	463	464	465
р	466	467	468	469	470	471	472	473	474	475	476	477	478	479	480	481	482	483	484	485	486	487	488	489	490	491	492	493	494	495	496
с	497	498	499	500	501	502	503	504	505	506	507	508	509	510	511	512	513	514	515	516	517	518	519	520	521	522	523	524	525	526	527
т	528	529	530	531	532	533	534	535	536	537	538	539	540	541	542	543	544	545	546	547	548	549	550	551	552	553	554	555	556	557	558
у	559	560	561	562	563	564	565	566	567	568	569	570	571	572	573	574	575	576	577	578	579	580	581	582	583	584	585	586	587	588	589
ф	590	591	592	593	594	595	596	597	598	599	600	601	602	603	604	605	606	607	608	609	610	611	612	613	614	615	616	617	618	619	620
х	621	622	623	624	625	626	627	628	629	630	631	632	633	634	635	636	637	638	639	640	641	642	643	644	645	646	647	648	649	650	651
ц	652	653	654	655	656	657	658	659	660	661	662	663	664	665	666	667	668	669	670	671	672	673	674	675	676	677	678	679	680	681	682
ч	683	684	685	686	687	688	689	690	691	692	693	694	695	696	697	698	699	700	701	702	703	704	705	706	707	708	709	710	711	712	713
ш	714	715	716	717	718	719	720	721	722	723	724	725	726	727	728	729	730	731	732	733	734	735	736	737	738	739	740	741	742	743	744
щ	745	746	747	748	749	750	751	752	753	754	755	756	757	758	759	760	761	762	763	764	765	766	767	768	769	770	771	772	773	774	775
ъ	776	777	778	779	780	781	782	783	784	785	786	787	788	789	790	791	792	793	794	795	796	797	798	799	800	801	802	803	804	805	806
ы	807	808	809	810	811	812	813	814	815	816	817	818	819	820	821	822	823	824	825	826	827	828	829	830	831	832	833	834	835	836	837
ь	838	839	840	841	842	843	844	845	846	847	848	849	850	851	852	853	854	855	856	857	858	859	860	861	862	863	864	865	866	867	868
э	869	870	871	872	873	874	875	876	877	878	879	880	881	882	883	884	885	886	887	888	889	890	891	892	893	894	895	896	897	898	899
ю	900	901	902	903	904	905	906	907	908	909	910	911	912	913	914	915	916	917	918	919	920	921	922	923	924	925	926	927	928	929	930
я	931	932	933	934	935	936	937	938	939	940	941	942	943	944	945	946	947	948	949	950	951	952	953	954	955	956	957	958	959	960	961

=== Input 2 ===
Enter a text to encrypt: Kotlin
Enter a supplementary character (must use the same alphabet as a word to encrypt), leaving blank makes it a random supplement:
Enter the encoding table type you want to use (0 - Standard, 1 - Randomized): 1
No supplement provided, thus a random character will be used as a supplement
=== Output 2 ===
Plaintext	ko	tl	in
Ciphertext	002	044	702
Decrypted	kotlin

	a	b	c	d	e	f	g	h	i	j	k	l	m	n	o	p	q	r	s	t	u	v	w	x	y	z
a	873	376	922	403	377	406	415	643	271	028	075	795	489	016	442	276	734	893	673	321	130	974	210	301	349	566
b	036	226	790	902	562	169	608	628	610	079	300	134	197	728	050	687	686	277	565	305	056	738	573	124	598	744
c	471	177	066	582	308	514	603	137	872	722	729	078	466	176	336	587	824	470	450	704	894	327	502	344	505	558
d	373	058	677	843	530	912	469	507	517	262	813	751	379	389	930	889	605	781	869	496	672	910	477	868	758	962
e	053	657	897	584	432	663	632	204	404	003	945	174	739	570	850	093	665	067	038	761	069	888	567	090	742	283
f	365	842	666	725	202	883	189	062	585	342	025	617	146	614	998	822	621	039	098	328	670	732	272	331	295	158
g	690	485	182	506	510	579	923	041	371	660	817	393	251	792	364	278	844	559	867	004	119	993	055	516	095	017
h	747	855	924	847	557	112	771	568	667	716	832	801	325	821	928	640	275	879	047	709	266	491	314	110	260	446
i	253	346	723	593	317	816	144	845	984	099	526	107	426	702	828	840	220	794	188	858	577	391	636	834	334	823
j	172	877	652	091	856	015	743	736	720	035	661	522	443	061	970	786	020	063	246	304	234	357	599	662	503	875
k	454	580	235	804	395	515	959	551	639	151	033	073	157	256	002	916	594	179	602	757	638	208	647	279	726	143
l	809	019	885	762	060	826	780	353	081	886	699	140	288	180	681	956	326	942	553	148	071	770	905	825	142	121
m	848	802	990	951	399	521	680	059	756	024	578	444	458	057	920	768	225	378	267	635	556	963	409	796	348	425
n	820	870	401	451	611	022	007	291	750	975	561	265	918	646	658	803	463	190	997	876	787	999	418	439	480	355
o	306	072	218	627	105	138	437	369	259	382	887	934	527	115	851	860	139	430	772	574	534	703	449	128	282	495
p	531	427	268	221	245	170	194	878	779	203	985	710	464	462	320	227	102	936	381	538	145	209	759	484	184	698
q	948	117	281	413	043	978	586	370	798	232	168	483	536	473	434	030	175	273	696	965	296	766	354	874	396	881
r	482	481	248	299	284	338	006	307	992	065	386	153	949	499	217	576	544	682	737	106	332	542	707	147	913	791
s	287	013	126	493	269	708	774	368	827	778	830	498	324	607	777	408	904	882	518	045	525	943	545	612	359	213
t	788	398	486	113	129	366	352	994	285	899	193	044	634	977	659	196	938	155	383	005	541	829	292	094	523	814
u	118	178	980	935	953	915	715	010	423	927	799	420	345	392	244	853	854	719	624	986	363	494	890	131	211	111
v	362	086	972	051	011	465	318	310	343	186	435	767	160	223	200	789	088	270	322	763	700	436	650	651	319	467
w	925	049	642	445	592	374	588	411	533	429	149	132	302	688	166	501	298	765	165	520	547	487	932	100	164	591
x	552	648	891	532	631	655	261	865	237	958	513	884	191	258	961	215	034	433	289	455	141	488	679	773	563	241
y	385	810	092	550	656	745	549	096	982	410	447	861	835	741	895	083	833	917	021	641	173	076	714	693	122	846
z	892	653	664	238	085	581	898	422	417	769	479	546	712	183	341	046	257	171	572	615	150	247	448	114	127	908

=== Input 3 ===
Enter a text to encrypt: Ко́тлин (Kotlin: /ˈkɒtlɪn/) — ООЯП.
Enter a supplementary character (must use the same alphabet as a word to encrypt), leaving blank makes it a random supplement: *
Enter the encoding table type you want to use (0 - Standard, 1 - Randomized): 0
Provided message cannot be fully encoded with any of the standard tables because of their restrictions: using the limited character set suitable to encode single words only consisting solely of English or Russian (omitting 'ё' and 'й' symbols) lowercase letters. Therefore, the best matching table will be applied via auto detection.
Provided supplement seems not to be in the specified range of keys, so the range will be extended to include the supplement character
=== Output 3 ===
Plaintext	Ко		́т		ли		н 		(K		ot		li		n:		 /		ˈk		ɒt		lɪ		n/		) 		— 		ОО		ЯП		.+*
Ciphertext	0001	0075	0149	0245	0297	0381	0805	0879	1069	0667	0741	0815	0889	0965	1037	1147	1185	1259
Decrypted	Ко́тлин (Kotlin: /ˈkɒtlɪn/) — ООЯП.*

=== Input 4 ===
Enter a text to encrypt: В небе тают облака, у кота растут бока.
Enter a supplementary character (must use the same alphabet as a word to encrypt), leaving blank makes it a random supplement:
Enter the encoding table type you want to use (0 - Standard, 1 - Randomized):
No supplement provided, thus a random character will be used as a supplement
=== Output 4 ===
Plaintext	В 		не		бе		 т		аю		т 		об		ла		ка		, 		у 		ко		та		 р		ас		ту		т 		бо		ка		.+Й
Ciphertext	20364	13345	19994	05101	06343	04622	17349	13692	10108	10167	09546	07089	00401	01055	11882	19519	04622	03323	10108	16708
Decrypted	В небе тают облака, у кота растут бока.Й

=== Input 5 ===
Enter a text to encrypt:
=== Output 5 ===
Invalid input: Message must not be empty
*/
