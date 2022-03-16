package com.kovan.lib.cipher.impl;


import java.nio.charset.Charset;

public class SeedKisaImpl {
    private static int ENDIAN = 0;
    private static final int[] SS0 = new int[]{696885672, 92635524, 382128852, 331600848, 340021332, 487395612, 747413676, 621093156, 491606364, 54739776, 403181592, 504238620, 289493328, 1020063996, 181060296, 591618912, 671621160, 71581764, 536879136, 495817116, 549511392, 583197408, 147374280, 386339604, 629514660, 261063564, 50529024, 994800504, 999011256, 318968592, 314757840, 785310444, 809529456, 210534540, 1057960764, 680042664, 839004720, 500027868, 919007988, 876900468, 751624428, 361075092, 185271048, 390550356, 474763356, 457921368, 1032696252, 16843008, 604250148, 470552604, 860058480, 411603096, 268439568, 214745292, 851636976, 432656856, 738992172, 667411428, 843215472, 58950528, 462132120, 297914832, 109478532, 164217288, 541089888, 272650320, 595829664, 734782440, 218956044, 914797236, 512660124, 256852812, 931640244, 441078360, 113689284, 944271480, 646357668, 302125584, 797942700, 365285844, 557932896, 63161280, 881111220, 21053760, 306336336, 1028485500, 227377548, 134742024, 521081628, 428446104, 0, 420024600, 67371012, 323179344, 935850996, 566354400, 1036907004, 910586484, 789521196, 654779172, 813740208, 193692552, 235799052, 730571688, 578986656, 776888940, 327390096, 223166796, 692674920, 1011642492, 151585032, 168428040, 1066382268, 802153452, 868479984, 96846276, 126321540, 335810580, 1053750012, 608460900, 516870876, 772678188, 189481800, 436867608, 101057028, 553722144, 726360936, 642146916, 33686016, 902164980, 310547088, 176849544, 202113036, 864269232, 1045328508, 281071824, 977957496, 122110788, 377918100, 633725412, 637936164, 8421504, 764256684, 533713884, 562143648, 805318704, 923218740, 781099692, 906375732, 352653588, 570565152, 940060728, 885321972, 663200676, 88424772, 206323788, 25264512, 701096424, 75792516, 394761108, 889532724, 197903304, 248431308, 1007431740, 826372464, 285282576, 130532292, 160006536, 893743476, 1003222008, 449499864, 952692984, 344232084, 424235352, 42107520, 80003268, 1070593020, 155795784, 956903736, 658989924, 12632256, 265274316, 398971860, 948482232, 252642060, 244220556, 37896768, 587408160, 293704080, 743202924, 466342872, 612671652, 872689716, 834793968, 138952776, 46318272, 793731948, 1024274748, 755835180, 4210752, 1049539260, 1041117756, 1015853244, 29475264, 713728680, 982168248, 240009804, 356864340, 990589752, 483184860, 675831912, 1062171516, 478974108, 415813848, 172638792, 373707348, 927429492, 545300640, 768467436, 105267780, 897954228, 722150184, 625303908, 986379000, 600040416, 965325240, 830583216, 529503132, 508449372, 969535992, 650568420, 847426224, 822161712, 717939432, 760045932, 525292380, 616882404, 817950960, 231588300, 143163528, 369496596, 973746744, 407392344, 348442836, 574775904, 688464168, 117900036, 855847728, 684253416, 453710616, 84214020, 961114488, 276861072, 709517928, 705307176, 445289112};
    private static final int[] SS1 = new int[]{943196208, -399980320, 741149985, -1540979038, -871379005, -601960750, -1338801229, -1204254544, -1406169181, 1612726368, 1410680145, -1006123069, 1141130304, 1815039843, 1747667811, 1478183763, -1073495101, 1612857954, 808649523, -1271560783, 673777953, -1608482656, -534592798, -1540913245, -804011053, -1877900911, 269549841, 67503618, 471600144, -1136882512, 875955762, 1208699715, -332410909, -2012706688, 1814842464, -1473738592, 337053459, -1006320448, 336987666, -197868304, -1073560894, 1141196097, -534658591, -736704814, 1010765619, 1010634033, -1945203070, -1743222640, 673712160, 1276005954, -197736718, 1010699826, -1541044831, -130430479, 202181889, -601894957, -669464368, 673909539, 1680229986, 2017086066, 606537507, 741281571, -265174543, 1882342002, 1073889858, -736836400, 1073824065, -1073692480, 1882407795, 1680295779, -1406366560, -2012509309, -197670925, -1406300767, -2147450752, 471797523, -938816830, 741084192, -1473607006, 875824176, -804076846, 134941443, -332476702, -399914527, 1545424209, -1810594672, 404228112, -130496272, 1410811731, -1406234974, 134744064, -1006254655, 269681427, -871510591, -2079947134, -1204188751, -62926861, 2084392305, -1073626687, 808517937, -197802511, -2012575102, 1747602018, -1338932815, -804142639, 538968096, -736639021, 131586, 539099682, 67372032, 1747470432, 1882276209, 67569411, -669266989, -1675784815, -1743156847, 1612792161, -1136750926, -467220766, 1478052177, -602026543, 1343308113, -1877966704, -602092336, -1743091054, -1608285277, -1473541213, -804208432, -2147384959, 202313475, 1141327683, 404359698, -534527005, -332608288, -1945268863, -1136685133, -1810463086, 2017151859, 1545358416, -1608351070, -1608416863, 1612923747, 539165475, 1275940161, -938948416, -1675719022, -1675850608, 943327794, 202116096, 741215778, -1204122958, 1814974050, -1675653229, 1478117970, -265108750, -1877835118, -265042957, 1208568129, 2016954480, -871576384, 336921873, -130298893, 1882210416, 1949648241, 2084523891, 875889969, 269484048, 197379, 1680098400, 1814908257, -1006188862, 1949582448, -736770607, -1271626576, -399848734, 134809857, 1949714034, 404293905, -62992654, 1073758272, 269615634, -534724384, -1136816719, 67437825, -130364686, 65793, -265240336, 673843746, 1545490002, -1473672799, 1410745938, 1073955651, -2080012927, 336856080, -2012640895, -1743025261, -1338998608, -467286559, 1208502336, 2017020273, -1810397293, -63124240, 471731730, -2147319166, 539033889, -1945334656, 404425491, 1545555795, 1949779827, 1410614352, -1338867022, 471665937, 606405921, 1276071747, 0, 1141261890, -332542495, 1477986384, 1343373906, -399782941, 2084458098, -669332782, -938882623, -63058447, 808452144, -1810528879, 1680164193, 1010568240, -1271494990, -467352352, -1204057165, 2084326512, 202247682, 1343242320, 943262001, 606471714, 808583730, -2080078720, 1747536225, -1877769325, 876021555, -467154973, 606340128, -1541110624, -938751037, 1343439699, 134875650, -2079881341, -669398575, 1275874368, -2147253373, -1945137277, -871444798, 943393587, 1208633922, -1271429197};
    private static final int[] SS2 = new int[]{-1582814839, -2122054267, -757852474, -741338173, 1347687492, 287055117, -1599329140, 556016901, 1364991309, 1128268611, 270014472, 303832590, 1364201793, -251904820, -1027077430, 1667244867, 539502600, 1078199364, 538976256, -1852039795, -522182464, -488627518, -1060632376, 320083719, -1583078011, -2087972977, 50332419, 1937259339, -1279771765, 319820547, -758115646, -487838002, 1886400576, -2138305396, 859586319, -1599592312, 842019330, -774103603, -218876218, 1886663748, -521392948, -1852566139, 50858763, 1398019911, 1348213836, 1398283083, -1313063539, 16777473, 539239428, 270277644, 1936732995, -1869080440, 269488128, -1060369204, -219139390, -774366775, 539765772, -471586873, 1919955522, -2088762493, -1818748021, -774893119, -2105276794, -1043854903, 1616912448, 1347424320, -1549786237, -471323701, 17566989, -1296812410, -1835262322, 1129058127, -1280034937, 1381505610, -1027340602, 1886926920, -1566300538, 303043074, -1548996721, -774629947, 1633689921, -1010826301, -1330367356, 1094713665, 1380979266, 1903967565, -2121527923, 526344, 320610063, -1852302967, 0, 286791945, 263172, 1397756739, -202098745, -505404991, -235127347, 1920218694, 590098191, 589571847, -1330630528, -2088236149, 34344462, -1549259893, -1566563710, 1651256910, -1819274365, 1095503181, 1634216265, 1887190092, 17303817, 34081290, -1279508593, -471060529, -202361917, -1044118075, -2088499321, 269751300, -218349874, 1617175620, -757326130, 573320718, 1128794955, 303569418, 33818118, 555753729, 1667771211, 1650730566, 33554946, -235653691, -1836051838, -2105013622, 789516, -1280298109, 1920745038, -791670592, 1920481866, 1128531783, -1835788666, -505141819, 572794374, -2139094912, -1582551667, -740548657, -1583341183, 808464384, 859059975, -1565774194, 842282502, 286528773, 572531202, 808990728, -252431164, -1549523065, 1094976837, 1078725708, -2122317439, -504878647, -2138831740, -1819011193, 825505029, -1010299957, -1026814258, 809253900, 1903178049, 286265601, -1010563129, -2121791095, 1903441221, -201835573, -757589302, -252167992, -1869343612, 1364728137, -2105539966, -1060895548, -201572401, 1095240009, 825768201, 1667508039, -1061158720, -1010036785, -741075001, -1330104184, 51121935, -2104750450, 1111491138, 589308675, -1852829311, 1617701964, -740811829, -1599855484, 808727556, -235916863, 1078462536, -1027603774, 1668034383, 826031373, 556543245, 1077936192, -1296286066, 842808846, -1329841012, -1044381247, -1566037366, -1296549238, 1112280654, 1364464965, 859323147, -790881076, 1617438792, 1937522511, -1868817268, -791144248, 1112017482, 1381242438, 1936996167, -1600118656, -504615475, 1111754310, -1313589883, 589835019, 1633953093, -218613046, -471850045, -1313326711, -1313853055, -1818484849, 1381768782, -235390519, -488364346, -1297075582, 825241857, -488101174, 1634479437, 1398546255, -521919292, -252694336, -1043591731, -2138568568, 303306246, 842545674, 1347950664, -791407420, 1650467394, 556280073, 50595591, 858796803, -521656120, 320346891, 17040645, 1903704393, -1869606784, 1650993738, 573057546, -1835525494};
    private static final int[] SS3 = new int[]{137377848, -924784600, 220277805, -2036161498, -809251825, -825041890, -2085375949, -2001684424, -1885098961, 1080057888, 1162957845, -943471609, 1145062404, 1331915823, 1264805931, 1263753243, -1010581501, 1113743394, 53686323, -2051951563, 153167913, -2136956896, -1025318878, -2019318745, -1009528813, -2121166831, 17895441, 100795398, 202382364, -1934574532, 103953462, 1262700555, -807146449, -2004842488, 1281387564, -2002737112, 118690839, -993999868, 101848086, -990841804, -1027424254, 1161905157, -1042161631, -959261674, 255015999, 221330493, -1904047090, -2003789800, 136325160, 1312967694, -957156298, 238173246, -2053004251, -906889159, 218172429, -808199137, -925837288, 186853419, 1180853286, 1249015866, 119743527, 253963311, -1041108943, 1114796082, 1111638018, -992947180, 1094795265, -1061109760, 1131638835, 1197696039, -1935627220, -1954314229, -940313545, -1918784467, -2139062272, 252910623, -893204470, 203435052, -1969051606, 70267956, -1026371566, 184748043, -823989202, -907941847, 1297177629, -2070899692, 135272472, -923731912, 1196643351, -1901941714, 134219784, -977157115, 51580947, -842937331, -2038266874, -1984841671, -806093761, 1299283005, -1044267007, 20000817, -973999051, -1971156982, 1247963178, -2119061455, -1043214319, 2105376, -942418921, 33685506, 35790882, 67109892, 1214277672, 1097953329, 117638151, -875309029, -1919837155, -1986947047, 1096900641, -1900889026, -958208986, 1230067737, -841884643, 1095847953, -2138009584, -858727396, -1970104294, -2086428637, -1952208853, -1060057072, -2122219519, 251857935, 1195590663, 168957978, -1008476125, -857674708, -1920889843, -1884046273, -2037214186, 1265858619, 1280334876, -2103271390, -2120114143, 1130586147, 52633635, 1296124941, -926889976, -1902994402, -1936679908, 171063354, 201329676, 237120558, -1967998918, 1315073070, -1886151649, 1246910490, -1024266190, -2104324078, -1007423437, 1229015049, 1215330360, -859780084, 85005333, -873203653, 1081110576, 1165063221, 1332968511, 87110709, 1052688, 50528259, 1147167780, 1298230317, -960314362, 1148220468, -976104427, -2068794316, -891099094, 151062537, 1181905974, 152115225, -822936514, 1077952512, 34738194, -1059004384, -1917731779, 83952645, -890046406, 16842753, -1057951696, 170010666, 1314020382, -1985894359, 1179800598, 1128480771, -2055109627, 68162580, -1987999735, -1953261541, -2135904208, -975051739, 1212172296, 1232173113, -2020371433, -856622020, 236067870, -2105376766, 18948129, -1937732596, 185800731, 1330863135, 1198748727, 1146115092, -2102218702, 219225117, 86058021, 1329810447, 0, 1178747910, -840831955, 1213224984, 1112690706, -874256341, 1316125758, -892151782, -910047223, -839779267, 3158064, -2054056939, 1164010533, 204487740, -2035108810, -991894492, -1951156165, 1282440252, 235015182, 1079005200, 154220601, 102900774, 36843570, -2071952380, 1231120425, -2087481325, 120796215, -941366233, 69215268, -2069847004, -876361717, 1129533459, 167905290, -2021424121, -908994535, 1279282188, -2088534013, -1887204337, -826094578, 187906107, 1245857802, -2018266057};
    private static final int LR_L0 = 0;
    private static final int LR_L1 = 1;
    private static final int LR_R0 = 2;
    private static final int LR_R1 = 3;
    private static final int BLOCK_SIZE_SEED = 16;
    private static final int BLOCK_SIZE_SEED_INT = 4;
    private static final int KC0 = -1640531527;
    private static final int KC1 = 1013904243;
    private static final int KC2 = 2027808486;
    private static final int KC3 = -239350324;
    private static final int KC4 = -478700647;
    private static final int KC5 = -957401293;
    private static final int KC6 = -1914802585;
    private static final int KC7 = 465362127;
    private static final int KC8 = 930724254;
    private static final int KC9 = 1861448508;
    private static final int KC10 = -572070280;
    private static final int KC11 = -1144140559;
    private static final int KC12 = 2006686179;
    private static final int KC13 = -281594938;
    private static final int KC14 = -563189875;
    private static final int KC15 = -1126379749;
    private static final int ABCD_A = 0;
    private static final int ABCD_B = 1;
    private static final int ABCD_C = 2;
    private static final int ABCD_D = 3;

    public SeedKisaImpl() {
    }

    private static final int EndianChange(int dwS) {
        return (dwS << 8 | dwS >> 24 & 255) & 16711935 | (dwS << 24 | dwS >> 8 & 16777215) & -16711936;
    }

    private static final byte GetB0(int A) {
        return (byte)(A & 255);
    }

    private static final byte GetB1(int A) {
        return (byte)(A >> 8 & 255);
    }

    private static final byte GetB2(int A) {
        return (byte)(A >> 16 & 255);
    }

    private static final byte GetB3(int A) {
        return (byte)(A >> 24 & 255);
    }

    private static final void SeedRound(int[] T, int[] LR, int L0, int L1, int R0, int R1, int[] K, int K_offset) {
        T[0] = LR[R0] ^ K[K_offset + 0];
        T[1] = LR[R1] ^ K[K_offset + 1];
        T[1] ^= T[0];
        T[1] = SS0[GetB0(T[1]) & 255] ^ SS1[GetB1(T[1]) & 255] ^ SS2[GetB2(T[1]) & 255] ^ SS3[GetB3(T[1]) & 255];
        T[0] += T[1];
        T[0] = SS0[GetB0(T[0]) & 255] ^ SS1[GetB1(T[0]) & 255] ^ SS2[GetB2(T[0]) & 255] ^ SS3[GetB3(T[0]) & 255];
        T[1] += T[0];
        T[1] = SS0[GetB0(T[1]) & 255] ^ SS1[GetB1(T[1]) & 255] ^ SS2[GetB2(T[1]) & 255] ^ SS3[GetB3(T[1]) & 255];
        T[0] += T[1];
        LR[L0] ^= T[0];
        LR[L1] ^= T[1];
    }

    private static void KISA_SEED_Encrypt_Block_forECB(int[] in, int in_offset, int[] out, int out_offset, KISA_SEED_KEY ks) {
        int[] LR = new int[4];
        int[] T = new int[2];
        int[] K = ks.key_data;
        LR[0] = in[in_offset + 0];
        LR[1] = in[in_offset + 1];
        LR[2] = in[in_offset + 2];
        LR[3] = in[in_offset + 3];
        if (0 != ENDIAN) {
            LR[0] = EndianChange(LR[0]);
            LR[1] = EndianChange(LR[1]);
            LR[2] = EndianChange(LR[2]);
            LR[3] = EndianChange(LR[3]);
        }

        SeedRound(T, LR, 0, 1, 2, 3, K, 0);
        SeedRound(T, LR, 2, 3, 0, 1, K, 2);
        SeedRound(T, LR, 0, 1, 2, 3, K, 4);
        SeedRound(T, LR, 2, 3, 0, 1, K, 6);
        SeedRound(T, LR, 0, 1, 2, 3, K, 8);
        SeedRound(T, LR, 2, 3, 0, 1, K, 10);
        SeedRound(T, LR, 0, 1, 2, 3, K, 12);
        SeedRound(T, LR, 2, 3, 0, 1, K, 14);
        SeedRound(T, LR, 0, 1, 2, 3, K, 16);
        SeedRound(T, LR, 2, 3, 0, 1, K, 18);
        SeedRound(T, LR, 0, 1, 2, 3, K, 20);
        SeedRound(T, LR, 2, 3, 0, 1, K, 22);
        SeedRound(T, LR, 0, 1, 2, 3, K, 24);
        SeedRound(T, LR, 2, 3, 0, 1, K, 26);
        SeedRound(T, LR, 0, 1, 2, 3, K, 28);
        SeedRound(T, LR, 2, 3, 0, 1, K, 30);
        if (0 != ENDIAN) {
            LR[0] = EndianChange(LR[0]);
            LR[1] = EndianChange(LR[1]);
            LR[2] = EndianChange(LR[2]);
            LR[3] = EndianChange(LR[3]);
        }

        out[out_offset + 0] = LR[2];
        out[out_offset + 1] = LR[3];
        out[out_offset + 2] = LR[0];
        out[out_offset + 3] = LR[1];
    }

    public static int[] chartoint32_for_SEED_ECB(byte[] in, int inLen) {
        int len;
        if (inLen % 4 > 0) {
            len = inLen / 4 + 1;
        } else {
            len = inLen / 4;
        }

        int[] data = new int[len];

        for(int i = 0; i < len; ++i) {
            Common.byte_to_int(data, i, in, i * 4, ENDIAN);
        }

        return data;
    }

    public static byte[] int32tochar_for_SEED_ECB(int[] in, int inLen) {
        byte[] data = new byte[inLen];
        int i;
        if (ENDIAN != 0) {
            for(i = 0; i < inLen; ++i) {
                data[i] = (byte)(in[i / 4] >> i % 4 * 8);
            }
        } else {
            for(i = 0; i < inLen; ++i) {
                data[i] = (byte)(in[i / 4] >> (3 - i % 4) * 8);
            }
        }

        return data;
    }

    public static byte[] SEED_ECB_Encrypt_long(byte[] pbszUserKey, byte[] pbData, int offset, int length) {
        int PLAINTEXT_LENGTH = pbData.length;
        KISA_SEED_INFO info = new KISA_SEED_INFO();
        SEED_ECB_init(info, KISA_ENC_DEC.KISA_ENCRYPT, pbszUserKey);
        int process_blockLeng = 32;
        int[] outbuf = new int[process_blockLeng];
        int[] nRetOutLeng = new int[]{0};
        int[] nPaddingLeng = new int[]{0};
        byte[] pbszPlainText = new byte[process_blockLeng];
        int nPlainTextPadding = 16 - PLAINTEXT_LENGTH % 16;
        byte[] pbszCipherText = new byte[PLAINTEXT_LENGTH + nPlainTextPadding];

        int i;
        int[] data;
        byte[] cdata;
        for(i = 0; i < PLAINTEXT_LENGTH - process_blockLeng; i += nRetOutLeng[0]) {
            System.arraycopy(pbData, i, pbszPlainText, 0, process_blockLeng);
            data = chartoint32_for_SEED_ECB(pbszPlainText, process_blockLeng);
            SEED_ECB_Process(info, data, process_blockLeng, outbuf, nRetOutLeng);
            cdata = int32tochar_for_SEED_ECB(outbuf, nRetOutLeng[0]);
            System.arraycopy(cdata, 0, pbszCipherText, i, nRetOutLeng[0]);
        }

        int remainleng = PLAINTEXT_LENGTH % process_blockLeng;
        if (remainleng == 0) {
            remainleng = process_blockLeng;
        }

        System.arraycopy(pbData, i, pbszPlainText, 0, remainleng);
        data = chartoint32_for_SEED_ECB(pbszPlainText, remainleng);
        SEED_ECB_Process(info, data, remainleng, outbuf, nRetOutLeng);
        cdata = int32tochar_for_SEED_ECB(outbuf, nRetOutLeng[0]);
        System.arraycopy(cdata, 0, pbszCipherText, i, nRetOutLeng[0]);
        i += nRetOutLeng[0];
        SEED_ECB_Close(info, outbuf, 0, nPaddingLeng);
        cdata = int32tochar_for_SEED_ECB(outbuf, nPaddingLeng[0]);
        System.arraycopy(cdata, 0, pbszCipherText, i, nPaddingLeng[0]);
        return pbszCipherText;
    }

    public static byte[] SEED_ECB_Encrypt(byte[] pbszUserKey, byte[] pbData, int offset, int length) {
        KISA_SEED_INFO info = new KISA_SEED_INFO();
        int[] nRetOutLeng = new int[]{0};
        int[] nPaddingLeng = new int[]{0};
        int nPlainTextPadding = 16 - length % 16;
        byte[] newpbszPlainText = new byte[length + nPlainTextPadding];
        Common.arraycopy(newpbszPlainText, pbData, length);
        byte[] pbszCipherText = new byte[length + nPlainTextPadding];
        SEED_ECB_init(info, KISA_ENC_DEC.KISA_ENCRYPT, pbszUserKey);
        int outlen = newpbszPlainText.length / 16 * 4;
        int[] outbuf = new int[outlen];
        int[] data = chartoint32_for_SEED_ECB(newpbszPlainText, length);
        SEED_ECB_Process(info, data, length, outbuf, nRetOutLeng);
        SEED_ECB_Close(info, outbuf, nRetOutLeng[0], nPaddingLeng);
        byte[] cdata = int32tochar_for_SEED_ECB(outbuf, nRetOutLeng[0] + nPaddingLeng[0]);
        Common.arraycopy(pbszCipherText, cdata, nRetOutLeng[0] + nPaddingLeng[0]);
        data = null;
        cdata = null;
        outbuf = null;
        return pbszCipherText;
    }

    public static void KISA_SEED_Decrypt_Block_forECB(int[] in, int in_offset, int[] out, int out_offset, KISA_SEED_KEY ks) {
        int[] LR = new int[4];
        int[] T = new int[2];
        int[] K = ks.key_data;
        LR[0] = in[in_offset + 0];
        LR[1] = in[in_offset + 1];
        LR[2] = in[in_offset + 2];
        LR[3] = in[in_offset + 3];
        if (0 != ENDIAN) {
            LR[0] = EndianChange(LR[0]);
            LR[1] = EndianChange(LR[1]);
            LR[2] = EndianChange(LR[2]);
            LR[3] = EndianChange(LR[3]);
        }

        SeedRound(T, LR, 0, 1, 2, 3, K, 30);
        SeedRound(T, LR, 2, 3, 0, 1, K, 28);
        SeedRound(T, LR, 0, 1, 2, 3, K, 26);
        SeedRound(T, LR, 2, 3, 0, 1, K, 24);
        SeedRound(T, LR, 0, 1, 2, 3, K, 22);
        SeedRound(T, LR, 2, 3, 0, 1, K, 20);
        SeedRound(T, LR, 0, 1, 2, 3, K, 18);
        SeedRound(T, LR, 2, 3, 0, 1, K, 16);
        SeedRound(T, LR, 0, 1, 2, 3, K, 14);
        SeedRound(T, LR, 2, 3, 0, 1, K, 12);
        SeedRound(T, LR, 0, 1, 2, 3, K, 10);
        SeedRound(T, LR, 2, 3, 0, 1, K, 8);
        SeedRound(T, LR, 0, 1, 2, 3, K, 6);
        SeedRound(T, LR, 2, 3, 0, 1, K, 4);
        SeedRound(T, LR, 0, 1, 2, 3, K, 2);
        SeedRound(T, LR, 2, 3, 0, 1, K, 0);
        if (0 != ENDIAN) {
            LR[0] = EndianChange(LR[0]);
            LR[1] = EndianChange(LR[1]);
            LR[2] = EndianChange(LR[2]);
            LR[3] = EndianChange(LR[3]);
        }

        out[out_offset + 0] = LR[2];
        out[out_offset + 1] = LR[3];
        out[out_offset + 2] = LR[0];
        out[out_offset + 3] = LR[1];
    }

    public static byte[] SEED_ECB_Decrypt_long(byte[] pbszUserKey, byte[] pbData, int offset, int length) {
        int CIPHERTEXT_LENGTH = pbData.length;
        byte[] result = new byte[]{0};
        KISA_SEED_INFO info = new KISA_SEED_INFO();
        int EncryptedMessage_length = CIPHERTEXT_LENGTH;
        if (CIPHERTEXT_LENGTH % 16 > 0) {
            System.out.print("DECRYPT FAIL! ");
        } else {
            SEED_ECB_init(info, KISA_ENC_DEC.KISA_DECRYPT, pbszUserKey);
            int process_blockLeng = 32;
            int[] outbuf = new int[process_blockLeng];
            int[] nRetOutLeng = new int[]{0};
            int[] nPaddingLeng = new int[]{0};
            byte[] cipherText = new byte[process_blockLeng];
            byte[] pbszPlainText = new byte[CIPHERTEXT_LENGTH];

            int i;
            int[] data;
            byte[] cdata;
            for(i = 0; i < EncryptedMessage_length - process_blockLeng; i += nRetOutLeng[0]) {
                System.arraycopy(pbData, i, cipherText, 0, process_blockLeng);
                data = chartoint32_for_SEED_ECB(cipherText, process_blockLeng);
                SEED_ECB_Process(info, data, process_blockLeng, outbuf, nRetOutLeng);
                cdata = int32tochar_for_SEED_ECB(outbuf, nRetOutLeng[0]);
                System.arraycopy(cdata, 0, pbszPlainText, i, nRetOutLeng[0]);
            }

            int remainleng = EncryptedMessage_length % process_blockLeng;
            if (remainleng == 0) {
                remainleng = process_blockLeng;
            }

            System.arraycopy(pbData, i, cipherText, 0, remainleng);
            data = chartoint32_for_SEED_ECB(cipherText, remainleng);
            SEED_ECB_Process(info, data, remainleng, outbuf, nRetOutLeng);
            if (SEED_ECB_Close(info, outbuf, nRetOutLeng[0], nPaddingLeng) > 0) {
                cdata = int32tochar_for_SEED_ECB(outbuf, remainleng - nPaddingLeng[0]);
                System.arraycopy(cdata, 0, pbszPlainText, i, remainleng - nPaddingLeng[0]);
                int message_length = i + remainleng - nPaddingLeng[0];
                result = new byte[message_length];
                System.arraycopy(pbszPlainText, 0, result, 0, message_length);
                data = null;
                cdata = null;
                Object var19 = null;
            } else {
                result = new byte[10];
                System.out.print("DECRYPT FAIL! ");
            }
        }

        return result;
    }

    public static byte[] SEED_ECB_Decrypt(byte[] pbszUserKey, byte[] pbData, int offset, int length) {
        KISA_SEED_INFO info = new KISA_SEED_INFO();
        int outlen = 0;
        int[] nRetOutLeng = new int[]{0};
        int[] nPaddingLeng = new int[]{0};
        if (length % 16 > 0) {
            byte[] result = null;
            return (byte[])result;
        } else {
            byte[] newpbszCipherText = new byte[length];
            Common.arraycopy(newpbszCipherText, pbData, length);
            byte[] pbszPlainText = new byte[length];
            SEED_ECB_init(info, KISA_ENC_DEC.KISA_DECRYPT, pbszUserKey);
            outlen = length / 16 * 4;
            int[] outbuf = new int[outlen];
            int[] data = chartoint32_for_SEED_ECB(newpbszCipherText, length);
            SEED_ECB_Process(info, data, length, outbuf, nRetOutLeng);
            if (SEED_ECB_Close(info, outbuf, nRetOutLeng[0], nPaddingLeng) > 0) {
                byte[] cdata = int32tochar_for_SEED_ECB(outbuf, nRetOutLeng[0] - nPaddingLeng[0]);
                Common.arraycopy(pbszPlainText, cdata, nRetOutLeng[0] - nPaddingLeng[0]);
                int message_length = nRetOutLeng[0] - nPaddingLeng[0];
                if (message_length < 0) {
                    message_length = 0;
                }

                byte[] result = new byte[message_length];
                System.arraycopy(pbszPlainText, 0, result, 0, message_length);
                data = null;
                cdata = null;
                outbuf = null;
                return result;
            } else {
                byte[] result = null;
                return (byte[])result;
            }
        }
    }

    public static int SEED_ECB_init(KISA_SEED_INFO pInfo, KISA_ENC_DEC enc, byte[] pbszUserKey) {
        int[] ABCD = new int[4];
        int[] T = new int[2];
        if (null != pInfo && null != pbszUserKey) {
            int[] K = pInfo.seed_key.key_data;
            pInfo.encrypt = enc.value;
            pInfo.last_block_flag = pInfo.buffer_length = 0;
            ABCD[0] = Common.byte_to_int(pbszUserKey, 0, ENDIAN);
            ABCD[1] = Common.byte_to_int(pbszUserKey, 4, ENDIAN);
            ABCD[2] = Common.byte_to_int(pbszUserKey, 8, ENDIAN);
            ABCD[3] = Common.byte_to_int(pbszUserKey, 12, ENDIAN);
            if (0 != ENDIAN) {
                ABCD[0] = EndianChange(ABCD[0]);
                ABCD[1] = EndianChange(ABCD[1]);
                ABCD[2] = EndianChange(ABCD[2]);
                ABCD[3] = EndianChange(ABCD[3]);
            }

            RoundKeyUpdate0(T, K, 0, ABCD, -1640531527);
            RoundKeyUpdate1(T, K, 2, ABCD, 1013904243);
            RoundKeyUpdate0(T, K, 4, ABCD, 2027808486);
            RoundKeyUpdate1(T, K, 6, ABCD, -239350324);
            RoundKeyUpdate0(T, K, 8, ABCD, -478700647);
            RoundKeyUpdate1(T, K, 10, ABCD, -957401293);
            RoundKeyUpdate0(T, K, 12, ABCD, -1914802585);
            RoundKeyUpdate1(T, K, 14, ABCD, 465362127);
            RoundKeyUpdate0(T, K, 16, ABCD, 930724254);
            RoundKeyUpdate1(T, K, 18, ABCD, 1861448508);
            RoundKeyUpdate0(T, K, 20, ABCD, -572070280);
            RoundKeyUpdate1(T, K, 22, ABCD, -1144140559);
            RoundKeyUpdate0(T, K, 24, ABCD, 2006686179);
            RoundKeyUpdate1(T, K, 26, ABCD, -281594938);
            RoundKeyUpdate0(T, K, 28, ABCD, -563189875);
            T[0] = ABCD[0] + ABCD[2] - -1126379749;
            T[1] = ABCD[1] - ABCD[3] + -1126379749;
            K[30] = SS0[GetB0(T[0]) & 255] ^ SS1[GetB1(T[0]) & 255] ^ SS2[GetB2(T[0]) & 255] ^ SS3[GetB3(T[0]) & 255];
            K[31] = SS0[GetB0(T[1]) & 255] ^ SS1[GetB1(T[1]) & 255] ^ SS2[GetB2(T[1]) & 255] ^ SS3[GetB3(T[1]) & 255];
            return 1;
        } else {
            return 0;
        }
    }

    public static int SEED_ECB_Process(KISA_SEED_INFO pInfo, int[] in, int inLen, int[] out, int[] outLen) {
        int nCurrentCount = 16;
        int in_offset = 0;
        int out_offset = 0;
        if (null != pInfo && null != in && null != out && 0 <= inLen) {
            if (1 == pInfo.encrypt) {
                in_offset = 0;

                for(out_offset = 0; nCurrentCount <= inLen; out_offset += 4) {
                    KISA_SEED_Encrypt_Block_forECB(in, in_offset, out, out_offset, pInfo.seed_key);
                    nCurrentCount += 16;
                    in_offset += 4;
                }

                outLen[0] = nCurrentCount - 16;
                pInfo.buffer_length = inLen - outLen[0];
                Common.memcpy(pInfo.ecb_buffer, in, in_offset, pInfo.buffer_length);
            } else {
                in_offset = 0;

                for(out_offset = 0; nCurrentCount <= inLen; out_offset += 4) {
                    KISA_SEED_Decrypt_Block_forECB(in, in_offset, out, out_offset, pInfo.seed_key);
                    nCurrentCount += 16;
                    in_offset += 4;
                }

                outLen[0] = nCurrentCount - 16;
                Common.memcpy(pInfo.ecb_last_block, (int[])out, out_offset - 4, 16);
            }

            return 1;
        } else {
            return 0;
        }
    }

    public static int SEED_ECB_Close(KISA_SEED_INFO pInfo, int[] out, int out_offset, int[] outLen) {
        outLen[0] = 0;
        if (null == out) {
            return 0;
        } else {
            int i;
            if (1 == pInfo.encrypt) {
                int nPaddngLeng = 16 - pInfo.buffer_length;

                for(i = pInfo.buffer_length; i < 16; ++i) {
                    Common.set_byte_for_int(pInfo.ecb_buffer, i, (byte)nPaddngLeng, ENDIAN);
                }

                KISA_SEED_Encrypt_Block_forECB(pInfo.ecb_buffer, 0, out, out_offset / 4, pInfo.seed_key);
                outLen[0] = 16;
            } else {
                int nPaddngLeng = Common.get_byte_for_int(pInfo.ecb_last_block, 15, ENDIAN);
                if (nPaddngLeng <= 0 || nPaddngLeng > 16) {
                    return 0;
                }

                for(i = nPaddngLeng; i > 0; --i) {
                    Common.set_byte_for_int(out, out_offset - i, (byte)0, ENDIAN);
                }

                outLen[0] = nPaddngLeng;
            }

            return 1;
        }
    }

    private static final void RoundKeyUpdate0(int[] T, int[] K, int K_offset, int[] ABCD, int KC) {
        T[0] = ABCD[0] + ABCD[2] - KC;
        T[1] = ABCD[1] + KC - ABCD[3];
        K[K_offset + 0] = SS0[GetB0(T[0]) & 255] ^ SS1[GetB1(T[0]) & 255] ^ SS2[GetB2(T[0]) & 255] ^ SS3[GetB3(T[0]) & 255];
        K[K_offset + 1] = SS0[GetB0(T[1]) & 255] ^ SS1[GetB1(T[1]) & 255] ^ SS2[GetB2(T[1]) & 255] ^ SS3[GetB3(T[1]) & 255];
        T[0] = ABCD[0];
        ABCD[0] = ABCD[0] >> 8 & 16777215 ^ ABCD[1] << 24;
        ABCD[1] = ABCD[1] >> 8 & 16777215 ^ T[0] << 24;
    }

    private static final void RoundKeyUpdate1(int[] T, int[] K, int K_offset, int[] ABCD, int KC) {
        T[0] = ABCD[0] + ABCD[2] - KC;
        T[1] = ABCD[1] + KC - ABCD[3];
        K[K_offset + 0] = SS0[GetB0(T[0]) & 255] ^ SS1[GetB1(T[0]) & 255] ^ SS2[GetB2(T[0]) & 255] ^ SS3[GetB3(T[0]) & 255];
        K[K_offset + 1] = SS0[GetB0(T[1]) & 255] ^ SS1[GetB1(T[1]) & 255] ^ SS2[GetB2(T[1]) & 255] ^ SS3[GetB3(T[1]) & 255];
        T[0] = ABCD[2];
        ABCD[2] = ABCD[2] << 8 ^ ABCD[3] >> 24 & 255;
        ABCD[3] = ABCD[3] << 8 ^ T[0] >> 24 & 255;
    }

    public static void SeedRoundKey(int[] pdwRoundKey, byte[] pbUserKey) {
        int[] ABCD = new int[4];
        int[] T = new int[2];
        ABCD[0] = Common.byte_to_int(pbUserKey, 0, ENDIAN);
        ABCD[1] = Common.byte_to_int(pbUserKey, 4, ENDIAN);
        ABCD[2] = Common.byte_to_int(pbUserKey, 8, ENDIAN);
        ABCD[3] = Common.byte_to_int(pbUserKey, 12, ENDIAN);
        if (0 != ENDIAN) {
            ABCD[0] = EndianChange(ABCD[0]);
            ABCD[1] = EndianChange(ABCD[1]);
            ABCD[2] = EndianChange(ABCD[2]);
            ABCD[3] = EndianChange(ABCD[3]);
        }

        RoundKeyUpdate0(T, pdwRoundKey, 0, ABCD, -1640531527);
        RoundKeyUpdate1(T, pdwRoundKey, 2, ABCD, 1013904243);
        RoundKeyUpdate0(T, pdwRoundKey, 4, ABCD, 2027808486);
        RoundKeyUpdate1(T, pdwRoundKey, 6, ABCD, -239350324);
        RoundKeyUpdate0(T, pdwRoundKey, 8, ABCD, -478700647);
        RoundKeyUpdate1(T, pdwRoundKey, 10, ABCD, -957401293);
        RoundKeyUpdate0(T, pdwRoundKey, 12, ABCD, -1914802585);
        RoundKeyUpdate1(T, pdwRoundKey, 14, ABCD, 465362127);
        RoundKeyUpdate0(T, pdwRoundKey, 16, ABCD, 930724254);
        RoundKeyUpdate1(T, pdwRoundKey, 18, ABCD, 1861448508);
        RoundKeyUpdate0(T, pdwRoundKey, 20, ABCD, -572070280);
        RoundKeyUpdate1(T, pdwRoundKey, 22, ABCD, -1144140559);
        RoundKeyUpdate0(T, pdwRoundKey, 24, ABCD, 2006686179);
        RoundKeyUpdate1(T, pdwRoundKey, 26, ABCD, -281594938);
        RoundKeyUpdate0(T, pdwRoundKey, 28, ABCD, -563189875);
        T[0] = ABCD[0] + ABCD[2] - -1126379749;
        T[1] = ABCD[1] - ABCD[3] + -1126379749;
        pdwRoundKey[30] = SS0[GetB0(T[0]) & 255] ^ SS1[GetB1(T[0]) & 255] ^ SS2[GetB2(T[0]) & 255] ^ SS3[GetB3(T[0]) & 255];
        pdwRoundKey[31] = SS0[GetB0(T[1]) & 255] ^ SS1[GetB1(T[1]) & 255] ^ SS2[GetB2(T[1]) & 255] ^ SS3[GetB3(T[1]) & 255];
    }

    public static void main(String[] args) {
        byte[] pbUserKey = new byte[]{-120, -29, 79, -113, 8, 23, 121, -15, -23, -13, -108, 55, 10, -44, 5, -119};
        byte[] pbData = new byte[]{-41, 109, 13, 24, 50, 126, -59, 98, -79, 94, 107, -61, 101, -84, 12, 15};
        byte[] pbData1 = new byte[]{0, 1};
        byte[] pbData2 = new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0, 1};
        byte[] pbCipher = new byte[50];
        byte[] pbPlain = new byte[16];
        System.out.print("\n");
        System.out.print("[ Test SEED reference code ECB ]\n");
        System.out.print("\n\n");
        System.out.print("Key\t\t: ");

        int PLAINTEXT_LENGTH;
        for(PLAINTEXT_LENGTH = 0; PLAINTEXT_LENGTH < 16; ++PLAINTEXT_LENGTH) {
            System.out.print(Integer.toHexString(255 & pbUserKey[PLAINTEXT_LENGTH]) + " ");
        }

        System.out.print("\n");
        System.out.print("Plaintext\t: ");

        for(PLAINTEXT_LENGTH = 0; PLAINTEXT_LENGTH < 16; ++PLAINTEXT_LENGTH) {
            System.out.print(Integer.toHexString(255 & pbData[PLAINTEXT_LENGTH]) + " ");
        }

        System.out.print("\n\n\n");
        pbCipher = SEED_ECB_Encrypt(pbUserKey, pbData, 0, 16);
        pbPlain = SEED_ECB_Decrypt(pbUserKey, pbCipher, 0, 32);
        System.out.print("Ciphertext(SEED_ECB_Encrypt)\t: ");

        for(PLAINTEXT_LENGTH = 0; PLAINTEXT_LENGTH < 32; ++PLAINTEXT_LENGTH) {
            System.out.print(Integer.toHexString(255 & pbCipher[PLAINTEXT_LENGTH]) + " ");
        }

        System.out.print("\n");
        System.out.print("Plaintext(SEED_ECB_Decrypt)\t: ");

        for(PLAINTEXT_LENGTH = 0; PLAINTEXT_LENGTH < 16; ++PLAINTEXT_LENGTH) {
            System.out.print(Integer.toHexString(255 & pbPlain[PLAINTEXT_LENGTH]) + " ");
        }

        System.out.print("\n\n\n");
        pbCipher = null;
        pbPlain = null;
        pbCipher = SEED_ECB_Encrypt(pbUserKey, pbData1, 0, 2);
        pbPlain = SEED_ECB_Decrypt(pbUserKey, pbCipher, 0, 16);
        System.out.print("Ciphertext(enc)\t: ");

        for(PLAINTEXT_LENGTH = 0; PLAINTEXT_LENGTH < 16; ++PLAINTEXT_LENGTH) {
            System.out.print(Integer.toHexString(255 & pbCipher[PLAINTEXT_LENGTH]) + " ");
        }

        System.out.print("\n");
        System.out.print("Plaintext(dec)\t: ");

        for(PLAINTEXT_LENGTH = 0; PLAINTEXT_LENGTH < 2; ++PLAINTEXT_LENGTH) {
            System.out.print(Integer.toHexString(255 & pbPlain[PLAINTEXT_LENGTH]) + " ");
        }

        System.out.print("\n\n");
        pbCipher = null;
        pbPlain = null;
        pbCipher = SEED_ECB_Encrypt(pbUserKey, pbData2, 0, 18);
        pbPlain = SEED_ECB_Decrypt(pbUserKey, pbCipher, 0, 32);
        System.out.print("Ciphertext(enc)\t: ");

        for(PLAINTEXT_LENGTH = 0; PLAINTEXT_LENGTH < 32; ++PLAINTEXT_LENGTH) {
            System.out.print(Integer.toHexString(255 & pbCipher[PLAINTEXT_LENGTH]) + " ");
        }

        System.out.print("\n");
        System.out.print("Plaintext(dec)\t: ");

        for(PLAINTEXT_LENGTH = 0; PLAINTEXT_LENGTH < 18; ++PLAINTEXT_LENGTH) {
            System.out.print(Integer.toHexString(255 & pbPlain[PLAINTEXT_LENGTH]) + " ");
        }

        System.out.print("\n\n");
        pbCipher = null;
        pbPlain = null;
        System.out.print("\n\n��� 2 \n[ Test Encrypt mode ]\n");
        System.out.print("Key\t\t: ");

        for(PLAINTEXT_LENGTH = 0; PLAINTEXT_LENGTH < 16; ++PLAINTEXT_LENGTH) {
            System.out.print(Integer.toHexString(255 & pbUserKey[PLAINTEXT_LENGTH]) + " ");
        }

        System.out.print("\n");
        System.out.print("Plaintext\t: ");

        for(PLAINTEXT_LENGTH = 0; PLAINTEXT_LENGTH < 16; ++PLAINTEXT_LENGTH) {
            System.out.print(Integer.toHexString(255 & pbData[PLAINTEXT_LENGTH]) + " ");
        }

        System.out.print("\n\n\n");
        PLAINTEXT_LENGTH = 16;
        KISA_SEED_INFO info = new KISA_SEED_INFO();
        SEED_ECB_init(info, KISA_ENC_DEC.KISA_ENCRYPT, pbUserKey);
        int process_blockLeng = 32;
        int[] outbuf = new int[process_blockLeng];
        int[] nRetOutLeng = new int[]{0};
        int[] nPaddingLeng = new int[]{0};
        byte[] pbszPlainText = new byte[process_blockLeng];
        int nPlainTextPadding = 16 - PLAINTEXT_LENGTH % 16;
        int CIPHERTEXT_LENGTH = PLAINTEXT_LENGTH + nPlainTextPadding;
        byte[] pbszCipherText = new byte[PLAINTEXT_LENGTH + nPlainTextPadding];

        int i;
        int[] data;
        byte[] cdata;
        for(i = 0; i < PLAINTEXT_LENGTH - process_blockLeng; i += nRetOutLeng[0]) {
            System.arraycopy(pbData, i, pbszPlainText, 0, process_blockLeng);
            data = chartoint32_for_SEED_ECB(pbszPlainText, process_blockLeng);
            SEED_ECB_Process(info, data, process_blockLeng, outbuf, nRetOutLeng);
            cdata = int32tochar_for_SEED_ECB(outbuf, nRetOutLeng[0]);
            System.arraycopy(cdata, 0, pbszCipherText, i, nRetOutLeng[0]);
        }

        int remainleng = PLAINTEXT_LENGTH % process_blockLeng;
        if (remainleng == 0) {
            remainleng = process_blockLeng;
        }

        System.arraycopy(pbData, i, pbszPlainText, 0, remainleng);
        data = chartoint32_for_SEED_ECB(pbszPlainText, remainleng);
        SEED_ECB_Process(info, data, remainleng, outbuf, nRetOutLeng);
        cdata = int32tochar_for_SEED_ECB(outbuf, nRetOutLeng[0]);
        System.arraycopy(cdata, 0, pbszCipherText, i, nRetOutLeng[0]);
        i += nRetOutLeng[0];
        SEED_ECB_Close(info, outbuf, 0, nPaddingLeng);
        cdata = int32tochar_for_SEED_ECB(outbuf, nPaddingLeng[0]);
        System.arraycopy(cdata, 0, pbszCipherText, i, nPaddingLeng[0]);
        System.out.print("Ciphertext(enc)\t: ");

        for(i = 0; i < CIPHERTEXT_LENGTH; ++i) {
            System.out.print(Integer.toHexString(255 & pbszCipherText[i]) + " ");
        }

        System.out.print("\n");
        data = null;
        cdata = null;
        outbuf = null;
        info = null;
        CIPHERTEXT_LENGTH = 32;
        byte[] result = new byte[]{0};
        info = new KISA_SEED_INFO();
        int EncryptedMessage_length = CIPHERTEXT_LENGTH;
        byte[] cipherText;
        if (CIPHERTEXT_LENGTH % 16 > 0) {
            System.out.print("DECRYPT FAIL! ");
        } else {
            SEED_ECB_init(info, KISA_ENC_DEC.KISA_DECRYPT, pbUserKey);
            process_blockLeng = 32;
            outbuf = new int[process_blockLeng];
            cipherText = new byte[process_blockLeng];
            pbszPlainText = new byte[CIPHERTEXT_LENGTH];

            for(i = 0; i < EncryptedMessage_length - process_blockLeng; i += nRetOutLeng[0]) {
                System.arraycopy(pbszCipherText, i, cipherText, 0, process_blockLeng);
                data = chartoint32_for_SEED_ECB(cipherText, process_blockLeng);
                SEED_ECB_Process(info, data, process_blockLeng, outbuf, nRetOutLeng);
                cdata = int32tochar_for_SEED_ECB(outbuf, nRetOutLeng[0]);
                System.arraycopy(cdata, 0, pbszPlainText, i, nRetOutLeng[0]);
            }

            remainleng = EncryptedMessage_length % process_blockLeng;
            if (remainleng == 0) {
                remainleng = process_blockLeng;
            }

            System.arraycopy(pbszCipherText, i, cipherText, 0, remainleng);
            data = chartoint32_for_SEED_ECB(cipherText, remainleng);
            SEED_ECB_Process(info, data, remainleng, outbuf, nRetOutLeng);
            if (SEED_ECB_Close(info, outbuf, nRetOutLeng[0], nPaddingLeng) > 0) {
                cdata = int32tochar_for_SEED_ECB(outbuf, remainleng - nPaddingLeng[0]);
                System.arraycopy(cdata, 0, pbszPlainText, i, remainleng - nPaddingLeng[0]);
                int message_length = i + remainleng - nPaddingLeng[0];
                result = new byte[message_length];
                System.arraycopy(pbszPlainText, 0, result, 0, message_length);
                data = null;
                cdata = null;
                outbuf = null;
            } else {
                result = new byte[10];
                System.out.print("DECRYPT FAIL! ");
            }
        }

        System.out.print("Plaintext(dec)\t: ");

        for(i = 0; i < PLAINTEXT_LENGTH; ++i) {
            System.out.print(Integer.toHexString(255 & result[i]) + " ");
        }

        System.out.print("\n\n");
        result = null;
        pbszCipherText = null;
        PLAINTEXT_LENGTH = 2;
        info = new KISA_SEED_INFO();
        SEED_ECB_init(info, KISA_ENC_DEC.KISA_ENCRYPT, pbUserKey);
        process_blockLeng = 32;
        outbuf = new int[process_blockLeng];
        pbszPlainText = new byte[process_blockLeng];
        nPlainTextPadding = 16 - PLAINTEXT_LENGTH % 16;
        CIPHERTEXT_LENGTH = PLAINTEXT_LENGTH + nPlainTextPadding;
        pbszCipherText = new byte[PLAINTEXT_LENGTH + nPlainTextPadding];

        for(i = 0; i < PLAINTEXT_LENGTH - process_blockLeng; i += nRetOutLeng[0]) {
            System.arraycopy(pbData, i, pbszPlainText, 0, process_blockLeng);
            data = chartoint32_for_SEED_ECB(pbszPlainText, process_blockLeng);
            SEED_ECB_Process(info, data, process_blockLeng, outbuf, nRetOutLeng);
            cdata = int32tochar_for_SEED_ECB(outbuf, nRetOutLeng[0]);
            System.arraycopy(cdata, 0, pbszCipherText, i, nRetOutLeng[0]);
        }

        remainleng = PLAINTEXT_LENGTH % process_blockLeng;
        if (remainleng == 0) {
            remainleng = process_blockLeng;
        }

        System.arraycopy(pbData, i, pbszPlainText, 0, remainleng);
        data = chartoint32_for_SEED_ECB(pbszPlainText, remainleng);
        SEED_ECB_Process(info, data, remainleng, outbuf, nRetOutLeng);
        cdata = int32tochar_for_SEED_ECB(outbuf, nRetOutLeng[0]);
        System.arraycopy(cdata, 0, pbszCipherText, i, nRetOutLeng[0]);
        i += nRetOutLeng[0];
        SEED_ECB_Close(info, outbuf, 0, nPaddingLeng);
        cdata = int32tochar_for_SEED_ECB(outbuf, nPaddingLeng[0]);
        System.arraycopy(cdata, 0, pbszCipherText, i, nPaddingLeng[0]);
        System.out.print("Ciphertext(enc)\t: ");

        for(i = 0; i < CIPHERTEXT_LENGTH; ++i) {
            System.out.print(Integer.toHexString(255 & pbszCipherText[i]) + " ");
        }

        System.out.print("\n");
        data = null;
        cdata = null;
        outbuf = null;
        info = null;
        CIPHERTEXT_LENGTH = 16;
        result = new byte[]{0};
        info = new KISA_SEED_INFO();
        EncryptedMessage_length = CIPHERTEXT_LENGTH;
        if (CIPHERTEXT_LENGTH % 16 > 0) {
            System.out.print("DECRYPT FAIL! ");
        } else {
            SEED_ECB_init(info, KISA_ENC_DEC.KISA_DECRYPT, pbUserKey);
            process_blockLeng = 32;
            outbuf = new int[process_blockLeng];
            cipherText = new byte[process_blockLeng];
            pbszPlainText = new byte[CIPHERTEXT_LENGTH];

            for(i = 0; i < EncryptedMessage_length - process_blockLeng; i += nRetOutLeng[0]) {
                System.arraycopy(pbszCipherText, i, cipherText, 0, process_blockLeng);
                data = chartoint32_for_SEED_ECB(cipherText, process_blockLeng);
                SEED_ECB_Process(info, data, process_blockLeng, outbuf, nRetOutLeng);
                cdata = int32tochar_for_SEED_ECB(outbuf, nRetOutLeng[0]);
                System.arraycopy(cdata, 0, pbszPlainText, i, nRetOutLeng[0]);
            }

            remainleng = EncryptedMessage_length % process_blockLeng;
            if (remainleng == 0) {
                remainleng = process_blockLeng;
            }

            System.arraycopy(pbszCipherText, i, cipherText, 0, remainleng);
            data = chartoint32_for_SEED_ECB(cipherText, remainleng);
            SEED_ECB_Process(info, data, remainleng, outbuf, nRetOutLeng);
            if (SEED_ECB_Close(info, outbuf, nRetOutLeng[0], nPaddingLeng) > 0) {
                cdata = int32tochar_for_SEED_ECB(outbuf, remainleng - nPaddingLeng[0]);
                System.arraycopy(cdata, 0, pbszPlainText, i, remainleng - nPaddingLeng[0]);
                PLAINTEXT_LENGTH = i + remainleng - nPaddingLeng[0];
                result = new byte[PLAINTEXT_LENGTH];
                System.arraycopy(pbszPlainText, 0, result, 0, PLAINTEXT_LENGTH);
                data = null;
                cdata = null;
                outbuf = null;
            } else {
                result = new byte[10];
                System.out.print("DECRYPT FAIL! ");
            }
        }

        System.out.print("Plaintext(dec)\t: ");

        for(i = 0; i < PLAINTEXT_LENGTH; ++i) {
            System.out.print(Integer.toHexString(255 & result[i]) + " ");
        }

        System.out.print("\n\n");
        result = null;
        pbszCipherText = null;
        PLAINTEXT_LENGTH = 18;
        info = new KISA_SEED_INFO();
        SEED_ECB_init(info, KISA_ENC_DEC.KISA_ENCRYPT, pbUserKey);
        process_blockLeng = 32;
        outbuf = new int[process_blockLeng];
        pbszPlainText = new byte[process_blockLeng];
        nPlainTextPadding = 16 - PLAINTEXT_LENGTH % 16;
        CIPHERTEXT_LENGTH = PLAINTEXT_LENGTH + nPlainTextPadding;
        pbszCipherText = new byte[PLAINTEXT_LENGTH + nPlainTextPadding];

        for(i = 0; i < PLAINTEXT_LENGTH - process_blockLeng; i += nRetOutLeng[0]) {
            System.arraycopy(pbData2, i, pbszPlainText, 0, process_blockLeng);
            data = chartoint32_for_SEED_ECB(pbszPlainText, process_blockLeng);
            SEED_ECB_Process(info, data, process_blockLeng, outbuf, nRetOutLeng);
            cdata = int32tochar_for_SEED_ECB(outbuf, nRetOutLeng[0]);
            System.arraycopy(cdata, 0, pbszCipherText, i, nRetOutLeng[0]);
        }

        remainleng = PLAINTEXT_LENGTH % process_blockLeng;
        if (remainleng == 0) {
            remainleng = process_blockLeng;
        }

        System.arraycopy(pbData2, i, pbszPlainText, 0, remainleng);
        data = chartoint32_for_SEED_ECB(pbszPlainText, remainleng);
        SEED_ECB_Process(info, data, remainleng, outbuf, nRetOutLeng);
        cdata = int32tochar_for_SEED_ECB(outbuf, nRetOutLeng[0]);
        System.arraycopy(cdata, 0, pbszCipherText, i, nRetOutLeng[0]);
        i += nRetOutLeng[0];
        SEED_ECB_Close(info, outbuf, 0, nPaddingLeng);
        cdata = int32tochar_for_SEED_ECB(outbuf, nPaddingLeng[0]);
        System.arraycopy(cdata, 0, pbszCipherText, i, nPaddingLeng[0]);
        System.out.print("Ciphertext(enc)\t: ");

        for(i = 0; i < CIPHERTEXT_LENGTH; ++i) {
            System.out.print(Integer.toHexString(255 & pbszCipherText[i]) + " ");
        }

        System.out.print("\n");
        data = null;
        cdata = null;
        outbuf = null;
        info = null;
        CIPHERTEXT_LENGTH = 32;
        result = new byte[]{0};
        info = new KISA_SEED_INFO();
        EncryptedMessage_length = CIPHERTEXT_LENGTH;
        if (CIPHERTEXT_LENGTH % 16 > 0) {
            System.out.print("DECRYPT FAIL! ");
        } else {
            SEED_ECB_init(info, KISA_ENC_DEC.KISA_DECRYPT, pbUserKey);
            process_blockLeng = 32;
            outbuf = new int[process_blockLeng];
            cipherText = new byte[process_blockLeng];
            pbszPlainText = new byte[CIPHERTEXT_LENGTH];

            for(i = 0; i < EncryptedMessage_length - process_blockLeng; i += nRetOutLeng[0]) {
                System.arraycopy(pbszCipherText, i, cipherText, 0, process_blockLeng);
                data = chartoint32_for_SEED_ECB(cipherText, process_blockLeng);
                SEED_ECB_Process(info, data, process_blockLeng, outbuf, nRetOutLeng);
                cdata = int32tochar_for_SEED_ECB(outbuf, nRetOutLeng[0]);
                System.arraycopy(cdata, 0, pbszPlainText, i, nRetOutLeng[0]);
            }

            remainleng = EncryptedMessage_length % process_blockLeng;
            if (remainleng == 0) {
                remainleng = process_blockLeng;
            }

            System.arraycopy(pbszCipherText, i, cipherText, 0, remainleng);
            data = chartoint32_for_SEED_ECB(cipherText, remainleng);
            SEED_ECB_Process(info, data, remainleng, outbuf, nRetOutLeng);
            if (SEED_ECB_Close(info, outbuf, nRetOutLeng[0], nPaddingLeng) > 0) {
                cdata = int32tochar_for_SEED_ECB(outbuf, remainleng - nPaddingLeng[0]);
                System.arraycopy(cdata, 0, pbszPlainText, i, remainleng - nPaddingLeng[0]);
                PLAINTEXT_LENGTH = i + remainleng - nPaddingLeng[0];
                result = new byte[PLAINTEXT_LENGTH];
                System.arraycopy(pbszPlainText, 0, result, 0, PLAINTEXT_LENGTH);
                data = null;
                cdata = null;
                outbuf = null;
            } else {
                result = new byte[10];
                System.out.print("DECRYPT FAIL! ");
            }
        }

        System.out.print("Plaintext(dec)\t: ");

        for(i = 0; i < PLAINTEXT_LENGTH; ++i) {
            System.out.print(Integer.toHexString(255 & result[i]) + " ");
        }

        System.out.print("\n\n");
        pbUserKey = "KIBNETCOOCON1004".getBytes(Charset.forName("EUC-KR"));
        pbData2 = "하하gk안!".getBytes(Charset.forName("EUC-KR"));
        pbCipher = null;
        pbPlain = null;
        pbCipher = SEED_ECB_Encrypt_long(pbUserKey, pbData2, 0, pbData2.length);
        pbPlain = SEED_ECB_Decrypt_long(pbUserKey, pbCipher, 0, pbCipher.length);
        System.out.print("Plain text\t: ");

        int j;
        for(j = 0; j < pbData2.length; ++j) {
            System.out.print(Integer.toHexString(255 & pbData2[j]) + " ");
        }

        System.out.print("\n");
        System.out.print("Ciphertext(SEED_ECB_Encrypt)\t: ");

        for(j = 0; j < pbCipher.length; ++j) {
            System.out.print(Integer.toHexString(255 & pbCipher[j]) + " ");
        }

        System.out.print("\n");
        System.out.print("Plaintext(SEED_ECB_Decrypt)\t: ");

        for(j = 0; j < pbData2.length; ++j) {
            System.out.print(Integer.toHexString(255 & pbPlain[j]) + " ");
        }

        System.out.print("\n\n\n");
    }

    public static class Common {
        public static final int BIG_ENDIAN = 0;
        public static final int LITTLE_ENDIAN = 1;
        public static final long INT_RANGE_MAX = (long)Math.pow(2.0D, 32.0D);

        public Common() {
        }

        public static void arraycopy(byte[] dst, byte[] src, int length) {
            for(int i = 0; i < length; ++i) {
                dst[i] = src[i];
            }

        }

        public static void arraycopy_offset(byte[] dst, int dst_offset, byte[] src, int src_offset, int length) {
            for(int i = 0; i < length; ++i) {
                dst[dst_offset + i] = src[src_offset + i];
            }

        }

        public static void arrayinit(byte[] dst, byte value, int length) {
            for(int i = 0; i < length; ++i) {
                dst[i] = value;
            }

        }

        public static void arrayinit_offset(byte[] dst, int dst_offset, byte value, int length) {
            for(int i = 0; i < length; ++i) {
                dst[dst_offset + i] = value;
            }

        }

        public static void memcpy(int[] dst, byte[] src, int length, int ENDIAN) {
            int iLen = length / 4;

            for(int i = 0; i < iLen; ++i) {
                byte_to_int(dst, i, src, i * 4, ENDIAN);
            }

        }

        public static void memcpy(int[] dst, int[] src, int src_offset, int length) {
            int iLen = length / 4 + (length % 4 != 0 ? 1 : 0);

            for(int i = 0; i < iLen; ++i) {
                dst[i] = src[src_offset + i];
            }

        }

        public static void set_byte_for_int(int[] dst, int b_offset, byte value, int ENDIAN) {
            int shift_value;
            int mask_value;
            int mask_value2;
            int value2;
            if (ENDIAN == 0) {
                shift_value = (3 - b_offset % 4) * 8;
                mask_value = 255 << shift_value;
                mask_value2 = ~mask_value;
                value2 = (value & 255) << shift_value;
                dst[b_offset / 4] = dst[b_offset / 4] & mask_value2 | value2 & mask_value;
            } else {
                shift_value = b_offset % 4 * 8;
                mask_value = 255 << shift_value;
                mask_value2 = ~mask_value;
                value2 = (value & 255) << shift_value;
                dst[b_offset / 4] = dst[b_offset / 4] & mask_value2 | value2 & mask_value;
            }

        }

        public static byte get_byte_for_int(int[] src, int b_offset, int ENDIAN) {
            int shift_value;
            int mask_value;
            int value;
            if (ENDIAN == 0) {
                shift_value = (3 - b_offset % 4) * 8;
                mask_value = 255 << shift_value;
                value = (src[b_offset / 4] & mask_value) >> shift_value;
                return (byte)value;
            } else {
                shift_value = b_offset % 4 * 8;
                mask_value = 255 << shift_value;
                value = (src[b_offset / 4] & mask_value) >> shift_value;
                return (byte)value;
            }
        }

        public static byte[] get_bytes_for_ints(int[] src, int offset, int ENDIAN) {
            int iLen = src.length - offset;
            byte[] result = new byte[iLen * 4];

            for(int i = 0; i < iLen; ++i) {
                int_to_byte(result, i * 4, src, offset + i, ENDIAN);
            }

            return result;
        }

        public static void byte_to_int(int[] dst, int dst_offset, byte[] src, int src_offset, int ENDIAN) {
            if (ENDIAN == 0) {
                dst[dst_offset] = (255 & src[src_offset]) << 24 | (255 & src[src_offset + 1]) << 16 | (255 & src[src_offset + 2]) << 8 | 255 & src[src_offset + 3];
            } else {
                dst[dst_offset] = 255 & src[src_offset] | (255 & src[src_offset + 1]) << 8 | (255 & src[src_offset + 2]) << 16 | (255 & src[src_offset + 3]) << 24;
            }

        }

        public static int byte_to_int(byte[] src, int src_offset, int ENDIAN) {
            return ENDIAN == 0 ? (255 & src[src_offset]) << 24 | (255 & src[src_offset + 1]) << 16 | (255 & src[src_offset + 2]) << 8 | 255 & src[src_offset + 3] : 255 & src[src_offset] | (255 & src[src_offset + 1]) << 8 | (255 & src[src_offset + 2]) << 16 | (255 & src[src_offset + 3]) << 24;
        }

        public static int byte_to_int_big_endian(byte[] src, int src_offset) {
            return (255 & src[src_offset]) << 24 | (255 & src[src_offset + 1]) << 16 | (255 & src[src_offset + 2]) << 8 | 255 & src[src_offset + 3];
        }

        public static void int_to_byte(byte[] dst, int dst_offset, int[] src, int src_offset, int ENDIAN) {
            int_to_byte_unit(dst, dst_offset, src[src_offset], ENDIAN);
        }

        public static void int_to_byte_unit(byte[] dst, int dst_offset, int src, int ENDIAN) {
            if (ENDIAN == 0) {
                dst[dst_offset] = (byte)(src >> 24 & 255);
                dst[dst_offset + 1] = (byte)(src >> 16 & 255);
                dst[dst_offset + 2] = (byte)(src >> 8 & 255);
                dst[dst_offset + 3] = (byte)(src & 255);
            } else {
                dst[dst_offset] = (byte)(src & 255);
                dst[dst_offset + 1] = (byte)(src >> 8 & 255);
                dst[dst_offset + 2] = (byte)(src >> 16 & 255);
                dst[dst_offset + 3] = (byte)(src >> 24 & 255);
            }

        }

        public static void int_to_byte_unit_big_endian(byte[] dst, int dst_offset, int src) {
            dst[dst_offset] = (byte)(src >> 24 & 255);
            dst[dst_offset + 1] = (byte)(src >> 16 & 255);
            dst[dst_offset + 2] = (byte)(src >> 8 & 255);
            dst[dst_offset + 3] = (byte)(src & 255);
        }

        public static int URShift(int x, int n) {
            if (n == 0) {
                return x;
            } else if (n >= 32) {
                return 0;
            } else {
                int v = x >> n;
                int v_mask = ~(-2147483648 >> n - 1);
                return v & v_mask;
            }
        }

        public static long intToUnsigned(int x) {
            return x >= 0 ? (long)x : (long)x + INT_RANGE_MAX;
        }
    }

    public static final class KISA_SEED_KEY {
        public int[] key_data = new int[32];

        public KISA_SEED_KEY() {
        }

        public void init() {
            for(int i = 0; i < this.key_data.length; ++i) {
                this.key_data[i] = 0;
            }

        }
    }

    public static final class KISA_ENC_DEC {
        public static final int _KISA_DECRYPT = 0;
        public static final int _KISA_ENCRYPT = 1;
        public int value;
        public static final KISA_ENC_DEC KISA_ENCRYPT = new KISA_ENC_DEC(1);
        public static final KISA_ENC_DEC KISA_DECRYPT = new KISA_ENC_DEC(0);

        public KISA_ENC_DEC(int value) {
            this.value = value;
        }
    }

    public static final class KISA_SEED_INFO {
        public int encrypt = 0;
        public int[] ivec = new int[4];
        public KISA_SEED_KEY seed_key = new KISA_SEED_KEY();
        public int[] ecb_buffer = new int[4];
        public int buffer_length;
        public int[] ecb_last_block = new int[4];
        public int last_block_flag;

        public KISA_SEED_INFO() {
            this.ivec[0] = this.ivec[1] = this.ivec[2] = this.ivec[3] = 0;
            this.seed_key.init();
            this.ecb_buffer[0] = this.ecb_buffer[1] = this.ecb_buffer[2] = this.ecb_buffer[3] = 0;
            this.buffer_length = 0;
            this.ecb_last_block[0] = this.ecb_last_block[1] = this.ecb_last_block[2] = this.ecb_last_block[3] = 0;
            this.last_block_flag = 0;
        }
    }
}
