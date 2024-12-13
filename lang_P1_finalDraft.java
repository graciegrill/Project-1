import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class lang_P1_finalDraft{
    /*
     * This is a function which finds all the letters denoted with a 0 in the guess word.
     * Parameters: String inputWord is the word that is analyzed for the 0 letters.
     * Returns: ArrayList<Character> charsNotInWord is an arraylist of all the characters associated with a 0.
     */
    public static ArrayList<Character> analyzeNegative(String inputWord){
        ArrayList<Character> charsNotinWord = new ArrayList<Character>();
        for (int i = 0; i<5;i++){
            if (inputWord.charAt(i+5) == '0'){
                charsNotinWord.add(inputWord.charAt(i));
            }
            else{
                continue;
            }
        }
        return charsNotinWord;
    }
    /*
     * This is a function which analyzes a guess word and finds all those letters associated with a 1 and their respective indices.
     * Parameters: String inputWord is the word that is analyzed for the 1 letters.
     * Returns: ArrayList<Character> charsInWord is an arraylist of all the characters associated with a 1 followed by their indices (e.g. a,1,b,2).
     */
    public static ArrayList<Character> analyzePositive(String inputWord){
        ArrayList<Character> charsInWord = new ArrayList<Character>();
        for (int i = 0; i<5;i++){
            if (inputWord.charAt(i+5) == '1'){
                charsInWord.add(inputWord.charAt(i));
                charsInWord.add((char)(i));
            }
            else{
                continue;
            }
        }
        return charsInWord;
    }
    /*
     * This is a function which analyzes a guess word and finds all those letters associated with a 2 and their respective indices.
     * Parameters: String inputWord is the word that is analyzed for the 2 letters.
     * Returns: ArrayList<Character> charPos is an arraylist of all the characters associated with a 2 followed by their indices (e.g. a,1,b,2).
     */
    public static ArrayList<Character> analyzeCharactersandPositions(String inputWord){
        ArrayList<Character> charPos = new ArrayList<Character>();
        for (int i = 0; i<5;i++){
            if (inputWord.charAt(i+5) == '2'){
                charPos.add(inputWord.charAt(i));
                charPos.add((char)(i));
            }
            else{
                continue;
            }
        }
        return charPos;
    }
    /*
     * This is a function which analyzes the words in the list of possibilities and compares them against the 0 ArrayList, 1 ArrayList, and 2 ArrayList to see if the word is posssible. 
     * Parameters: String analysis word is the word to be checked. ArrayList<Character>charsInWord, ArrayList<Character>oneChars, ArrayList<Character>twoChars are the 0 related ArrayList, the 1 related ArrayList, and the 2 related ArrayList respectively.
     * Returns: Boolean isNotInWord is a boolean value which is returned as true if a particular character in the 0 list doesn't appear in the word or appears in the other two lists otherwise.
     * This accomodates a situation in which one letter is both green and gray in the same word. It is returned as false otherwise.
     */
    public static Boolean compareNegatives(String analysisWord, ArrayList<Character>charsInWord, ArrayList<Character>oneChars, ArrayList<Character>twoChars){
        Boolean isNotInWord = true;
        for (int i = 0; i<charsInWord.size();i++){
            int val = analysisWord.indexOf(charsInWord.get(i));
            if(val<0 || oneChars.contains(charsInWord.get(i)) || twoChars.contains(charsInWord.get(i))){
                continue;
            }
            else{
                isNotInWord = false;
                break;
            }
        }
        return isNotInWord;

    }
    /*
     * This is a function which analyzes the words in the list of possibilities and compares them against an ArrayList to see if the word is posssible. 
     * Parameters: String analysis word is the word to be checked. ArrayList<Character>charsInWord is the 1 related ArrayList.
     * Returns: Boolean isNotInWord is a boolean value which is returned as true if a particular character appears in the word and not at the value designated as incorrect. It is false otherwise.
     */
    public static Boolean comparePositives(String analysisWord, ArrayList<Character>charsInWord){
        Boolean isInWord = true;
        for (int i = 0; i<charsInWord.size(); i+=2){
            if(analysisWord.contains(charsInWord.get(i).toString()) && analysisWord.charAt((int)charsInWord.get(i+1)) != charsInWord.get(i)){
                continue;
            }
            else{
                isInWord = false;
                break;
            }
        }
        return isInWord;

    }
    /*
     * This is a function which analyzes the words in the list of possibilities and compares them against an ArrayList to see if the word is posssible. 
     * Parameters: String analysis word is the word to be checked. ArrayList<Character>charsInWord is the 2 related ArrayList.
     * Returns: Boolean doesFit is a boolean value which is returned as true if a particular character appears in the word and at the value designated as correct. It is false otherwise.
     */
    public static Boolean wordFit(String analysisWord, ArrayList<Character> charPos){
        Boolean doesFit = true;
        for (int j = 0; j<charPos.size();j+=2){
            if (analysisWord.charAt(charPos.get(j+1)) == charPos.get(j)){
                continue;
            }
            else{
                doesFit = false;
                break;
            }
        }
        return doesFit;
    }
    /*
     * This is a function which checks that the guess string starts with 5 letters and ends with 5 numbers (0,1, or 2).
     * Parameters: String inputWord is the word to be checked.
     * Returns: Boolean valid which is indicative of validity or lack thereof.
     */
    public static Boolean wordValid(String inputWord){
        Boolean valid = true;
        for (int i = 0; i<5;i++){
            if (Character.isLetter(inputWord.charAt(i)) && (((inputWord.charAt(i+5)) =='0') || ((inputWord.charAt(i+5)) =='1')|| ((inputWord.charAt(i+5)) =='2'))){
                continue;           
            }
            else{
                valid = false;
                break;
            }
        }
        return valid;
    }


    public static void main(String[] args){
        ArrayList <String> acceptableWords = new ArrayList<String>(); //ArrayList of the words that are returned by the analysis of each guess word.
        ArrayList <String> finalWords = new ArrayList<String>(); //Consensus list of the words that each guess word agreees on.
        Scanner in = new Scanner(System.in);

        //Input guess words as [five letter word][numeric sequence], [...]. Other forms of input will be considered incorrect.
        System.out.println("Enter your input: ");

        //Splits guesses according to acceptable format
        String[] inputWordList = in.nextLine().toLowerCase().split(", ");
            for (int i = 0; i<inputWordList.length;i++){
                String inputWord = inputWordList[i];

                //Checks if length fits and whether the word is a valid guess.
                if(inputWord.length() == 10 && wordValid(inputWord)){
                    //Creates the appropriate arrays for the guess.
                    ArrayList<Character> negativeChars = analyzeNegative(inputWord);
                    ArrayList<Character> positiveChars = analyzePositive(inputWord);
                    ArrayList<Character> positionChars = analyzeCharactersandPositions(inputWord);
                    
                    ArrayList<String> incorrectWords = new ArrayList<String>();

                        //This adds any guesses that are not the correct word to a list to ensure that they aren't listed as a possibility subsequently. 
                        if(positionChars.size()<5){
                            incorrectWords.add(inputWord);
                        }

                        in.close();

                            try{
                            //Opens the appropriate text file for possible words.    
                            File fiveWords = new File("fives.txt");
                            Scanner wordFile = new Scanner(fiveWords);

                            //Checks the words against the lists and adds to a list of acceptable words.
                            while(wordFile.hasNextLine()){
                                String testWord = wordFile.nextLine();
                                    if (!incorrectWords.contains(testWord) && compareNegatives(testWord, negativeChars, positiveChars, positionChars) && comparePositives(testWord,positiveChars) && wordFit(testWord, positionChars)&& i<1){
                                        acceptableWords.add(testWord);
                                    }
                                    else if (!incorrectWords.contains(testWord) && compareNegatives(testWord, negativeChars, positiveChars, positionChars) && comparePositives(testWord,positiveChars) && wordFit(testWord, positionChars) && acceptableWords.contains(testWord)){
                                        acceptableWords.add(testWord);
                                    }
                            }
                            
                            wordFile.close();
                            }
                            catch(FileNotFoundException x){
                                System.out.println("File not found");
                            }}
                    else{
                        //This is what is done if input is empty or invalid.
                        try{
                            File fiveWords = new File("fives.txt");
                            Scanner wordFile = new Scanner(fiveWords);
                            while(wordFile.hasNextLine()){
                                    System.out.println(wordFile.nextLine());
                                }
                            System.out.println("You either have invalid input or did not submit any guesses.");
                            wordFile.close();
                                    }

                                    catch(FileNotFoundException x){
                                        System.out.println("File not found");
                                    }
                            }
                        //Creates a hashmap of the acceptable words to track how often they occur. If they occur as many times as the number of guesses, it is a consensus guess.
                        HashMap<String, Integer> wordCounter = new HashMap<>();
                        for (String word : acceptableWords) {
                            if(wordCounter.containsKey(word)){
                                wordCounter.put(word, wordCounter.get(word)+1); 
                            }
                            else{
                                wordCounter.put(word, 1);
                            }
                            }
                        //Adds the consensus words to final word list.
                        for (Map.Entry<String, Integer> entry : wordCounter.entrySet()) {
                            if (entry.getValue().equals(inputWordList.length)) {
                                finalWords.add(entry.getKey());
                            }
                            else{
                                continue;
                            }
                        //Prints the final list of acceptable, consensus words amongst all the guesses.
                        System.out.println(finalWords.get(finalWords.size()-1));
                        }
                    }}
                    }
/*
Sample input/output
 * Enter your input: 
arise00001, cleft01100, lemon22000
leggy
level
ledgy
lezzy

Given arise0001 as input
...
zoril
zoris
zowie
zymes
You either have invalid input or did not submit any guesses.

Enter your input: 
arise00001, ethyl11001
fleet
telex
gleet
lunet
owlet
lento
clept
culet
blent
veldt
unlet
knelt
dwelt
bluet
toled
cleft
delft
luted
letup

Enter your input: 
grace22222
grace

Enter your input: 
rstln00000
vague
mahoe
jivey
befog
dykey
vegie
keeve
quiff
dyked
gaped
cocoa
kooky
acock
abaka
baked
hobby
hiked
puked
ahead
dagga
iodid
iodic
bumph
didie
femme
faced
adage
poach
cocky
moggy
coped
moxie
gyved
moved
odium
caked
weedy
mewed
yapok
chimb
zooid
chime
vexed
goyim
wodge
boche
check
cymae
baboo
jawed
dumpy
pukka
wacky
eaved
hodad
emyde
havoc
upbye
miaou
miaow
gemmy
agaze
cakey
chimp
cheek
kicky
cheep
hapax
joked
pocky
hempy
okapi
yechy
bedim
vouch
jokey
zombi
whaup
gemma
cebid
kabab
howff
bocce
diazo
bocci
heigh
huzza
movie
bipod
howdy
vacua
babka
dopey
dummy
doped
hexed
puggy
boozy
cameo
judge
weepy
zoeae
bewig
imbed
defog
keyed
faggy
cabob
wiggy
abaca
bovid
coach
guaco
wived
abaci
pyoid
epoch
fugio
mambo
aback
adeem
adoze
fidge
dazed
hacek
hamza
achoo
epode
bedew
cheap
agave
womby
jaded
jehad
mamba
aphid
gibed
odeum
chide
mocha
quack
hexad
duomo
yawed
wimpy
feoff
chook
duomi
yomim
yoked
vapid
woody
duked
chick
chico
boggy
foamy
agape
faxed
wooed
cocci
fuggy
ceded
hemic
medii
medic
media
bogie
dumky
vodka
pampa
mamey
paged
quipu
chief
dumka
booze
gaged
geoid
oxeye
mauve
axiom
hoppy
mooch
dikey
couch
decay
bohea
hoked
hokey
decaf
diked
jugum
wavey
cimex
dough
yowie
chewy
cache
picky
pommy
amaze
deedy
piped
waved
chape
pudic
bimah
cycad
peppy
deoxy
paddy
bimbo
goofy
maybe
whizz
kyack
ogive
gombo
bucko
caped
pudgy
mujik
moody
mooed
hawed
hooky
yowed
dodgy
gighe
pokey
dodge
beigy
coude
beige
bowed
cupid
hooka
oxide
hajji
jacky
amuck
dobby
japed
huffy
evoke
goody
gappy
waged
homed
zowie
peavy
homey
douce
champ
jazzy
poove
pogey
gooey
whack
gizmo
bazoo
yucky
bemix
gecko
pubic
waxed
buxom
mezzo
fixed
chevy
dexie
gimpy
guyed
video
pawky
cowed
vuggy
fique
obeah
pizza
hayed
acidy
egged
dowie
epoxy
poxed
mayed
oozed
imido
bacca
guava
imide
happy
hooey
cacao
ghazi
oomph
zappy
embed
pewee
hived
educe
kapok
guppy
omega
geeky
hoody
bebop
duchy
imbue
diode
jimmy
hooch
immix
wacko
wacke
hypha
coyed
vowed
dacha
madam
aided
cavie
dowdy
ducky
kappa
kayak
dekko
dowed
acmic
ceiba
duped
muddy
baggy
deify
ackee
oxime
demob
mawed
heady
waive
ebbed
debye
gimme
quick
ohmic
biffy
widow
yobbo
ivied
abamp
caged
jimpy
bumpy
deked
awoke
cagey
yecch
campi
hyped
campo
embay
miche
campy
bijou
wowed
weigh
mafic
mommy
biddy
chivy
caeca
mixup
mafia
bided
bifid
caved
deice
fugue
fuzzy
chemo
debug
exude
facia
upbow
momma
whamo
chive
ogham
jived
gouge
dweeb
chafe
buggy
chaff
booby
dicey
offed
maced
beady
jumpy
kebob
wedge
magma
awake
heugh
kibbe
eched
dawed
webby
jiffy
kibbi
mucid
combe
fogey
hippo
peach
combo
gumbo
diced
hokum
hippy
puppy
peace
khadi
dauby
dhobi
mowed
jammy
edify
podia
doomy
civie
civic
affix
fizzy
aggie
mucky
duded
adobe
domic
koppa
fadge
pique
daube
wedgy
abohm
booed
kibei
adobo
apace
duddy
foxed
pappi
mimed
added
mimeo
mouch
cooch
imped
faddy
heuch
coupe
imago
pixie
which
image
mache
faded
whoop
aquae
pecky
muzzy
whoof
cobia
iambi
degum
faugh
pappy
peage
piece
bikie
apeek
xebec
macho
chuck
gadid
comae
fichu
cooee
viewy
cooed
whiff
gabby
fudge
gigue
chufa
yeuky
cooey
hewed
ovoid
gawky
chuff
domed
kazoo
woozy
vogie
decoy
yummy
jibed
chyme
abode
pooch
hadji
beach
idiom
avoid
feeze
jaggy
cuppa
boded
goopy
opium
payee
oxbow
gaddi
khaki
modem
mimic
addax
miked
payed
gauge
baiza
peaky
dobie
baize
aimed
heavy
bough
fiche
biked
biome
becap
beefy
dived
hokku
mecca
apeak
abbey
gaffe
bobby
widdy
heave
piggy
gaudy
vogue
maize
cuppy
joyed
poofy
ajiva
khaph
codex
cough
haded
hafiz
haugh
pawed
coded
codec
baaed
yucch
poked
yucca
jemmy
hoick
mixed
wifed
umped
heeze
boffo
embow
equid
pigmy
wheep
peeve
cobby
gooky
douma
beech
cohog
equip
coypu
puffy
fayed
jowed
waked
hubby
haika
zippy
edema
mummy
giddy
coved
pupae
fakey
umiac
bayou
abeam
above
mamie
umiak
covey
moped
haiku
viced
ached
choke
fifed
oidia
ameba
badge
upped
boxed
beaux
fazed
doozy
yogic
civvy
choky
jewed
jumbo
waded
wiped
poppy
agama
geode
quaff
adieu
azido
waddy
audio
paved
phage
poppa
azide
umiaq
faked
dogma
dippy
gammy
chomp
chiao
ephod
mopey
bogey
kebab
gamma
mamma
amido
fauve
gauzy
cadge
quaky
dewed
baffy
hided
pyxie
coxed
cozie
kabob
doggo
cahow
budge
yahoo
cadgy
doggy
doxie
maqui
bubby
gauze
byway
divvy
gazed
myoid
hammy
mazed
miffy
yogee
pucka
quake
cabby
kedge
pagod
fuzed
fuzee
deuce
muggy
baddy
gummy
ajuga
vivid
hakim
pavid
jambe
whump
amiga
khoum
admix
cubic
paced
compo
hazed
mammy
hyoid
jihad
middy
dogie
amigo
imaum
kudzu
gumma
gamic
humpy
famed
biped
aboma
humph
juked
coxae
midge
buffy
gamey
guide
deave
kopek
dizzy
kamik
kaiak
cubby
myoma
abuzz
kheda
pekoe
chock
cecum
beamy
bawdy
myope
hoagy
dogey
hoped
fumed
commy
papaw
amice
emcee
zamia
pacha
whomp
buddy
audad
azoic
aahed
cubeb
cozey
pouch
cubed
amici
gamed
abide
juice
comma
amide
boomy
oohed
feued
juicy
oakum
caddy
dewax
queue
magic
voice
wahoo
begum
fogie
gamba
pujah
gambe
aecia
vichy
gamay
zebec
ouphe
cooky
evade
cawed
bombe
dozed
poyou
maxim
podgy
bayed
jocko
gybed
boogy
daffy
comfy
myopy
waugh
kiddo
ephah
pouff
pygmy
hedge
kiddy
kopje
hedgy
mavie
comix
dicky
feaze
foggy
buffo
macaw
humic
piked
humid
buffi
cuddy
daddy
abmho
chump
beaky
fovea
coked
edged
comic
weave
coomb
 */