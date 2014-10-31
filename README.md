Oblig2
======
Jeg tenker at kompleksiteten til mitt program er litt vanskelig å beregne, da det forekommer mye
rekursjon her som er lineær, så jeg vil våge å påstå at mitt program er O(N) når det kommer til kompleksitet

Det ser ut som at jeg har fått koden til å fungere optimalt nå, eneste er at jeg har latt være å
inkludere at man må kjøre programmet ved å sette antall ansatte,
da jeg ikke har tatt hensyn til det
i min implementasjon og det blir unødvendig å gi programmet ett argument som ikke kommer til å bruke
uansett.

Programmet beregner nå riktig earliest/latest start (Wohooo!) og skriver ut riktig utskrift for kjøringen
av prosjektet som man skanner inn.

Grunnen til at det finnes en task 0 i programmet, er at dette er for å holde på alle tasks som kan
kan starte paralelt i begynnelsen av prosjektet. Task 0 gjør dette lettere.

Kompilering er bare å kompilere alle filene med javac *.java

Kjøring av programmet er: java Oblig2 "navn på fil"
