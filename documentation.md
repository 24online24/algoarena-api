# AlgoArena

## 1. Introducere

### 1.1 Context general

Programarea este un interes comun al ambilor membrii ai echipei noastre. De-a lungul timpului am utilizat diferite platforme dedicate învățării și testării abilităților când vine vorba de algoritmi și structuri de date.

Aceste platforme includ pbinfo.ro și leetcode.com, în cadrul căror există o gamă largă de probleme de rezolvat, iar utilizatorii își încarcă soluțiile (codul sursă în diferitele limbaje de programare suportate) spre a fi evaluate.

Un alt tip de platoformă este Advent of Code, care oferă probleme de rezolvat în fiecare zi a lunii decembrie, până în ziua de Crăciun. În cadrul acestei platforme, fiecare utilizator primește un input diferit, pentru care trebuie să încarce doar rezultatul final. Astfel, fiecare persoană poate rezolva problema folosid limbajul de programare preferat, sau chiar alte unelte, precum Excel sau SQL.

Noi am fost interesați de ideea de rulare și evaluare a codului, deci am decis să dezvoltăm o platformă similară cu prima categorie.

### 1.2 Obiective

Dezvoltarea unei platforme web care să ofere utilizatorilor posibilitatea de a rezolva probleme de algoritmizare și structuri de date, similară cu cele menționate la secțiunea 1.1. Mai mult decât atât, platforma încurajează utilizatorii să creeze propriile probleme pentru a fi rezolvate de alți utilizatori, în spiritul open-source.

Obiectivele principale ale acestei platforme sunt:

- oferirea unei interfețe ușor de utilizat pentru utilizator
- rezolvarea și evaluarea soluțiilor într-un mediu izolat și evaluarea acestuia, oferind suport pentru mai multe limbaje de programare decât platformele studiate
- oferirea unui sistem de autentificare și autorizare, pentru a proteja datele utilizatorilor
- oferirea unui sistem de persistență pentru probleme și soluții

### 1.3 Specificații

Utilizatorii au următoarele funcționalități la dispoziție:

- crearea unui cont/ autentificare
- crearea problemelor de rezolvat
- rezolvarea problemelor create de alți utilizatori (sau de ei înșiși) folosind diferite limbaje de programare
- vizualizarea soluțiilor proprii
- editarea profilului personal

Aceste funcționalități principale oferă o bază solidă pentru dezvoltarea ulterioară a platformei, precum adăugarea de funcții sociale, precum sistem de rating, mesagerie, etc.

## 2. Analiză, proiectare și implementare

### 2.1 Arhitectura aplicației

Arhitectura aplicației este împărțită în două componente principale, alături de câteva servicii adiacente. Componentele principale sunt frontend-ul (`algoarena-client`) și backend-ul `algoarena-api`, iar serviciile utilizate sunt baza de date și verificatorul soluțiilor.

Fiecare acțiune a utilizatorului are loc în frontend, care trimite cereri HTTP către backend (un API de tip REST). Acesta din urmă se ocupă de securizarea și procesarea cererilor, precum și de comunicarea cu baza de date și evaluatorul soluțiilor.

### 2.2 Tehnologii utilizate

Frontend-ul este dezvoltat folosind Vue.js, un framework JavaScript pentru construirea interfețelor web. Acesta este unul dintre cele mai populare framework-uri de acest tip, fiind ușor de învățat și de utilizat.

[!!!]

Backend-ul este dezvoltat folosind Spring Boot, un framework Java pentru dezvoltarea de aplicații web. Acesta este unul dintre cele mai populare framework-uri de acest tip, fiind ușor de învățat și de utilizat. De asemenea, Spring Boot oferă suport pentru multe alte tehnologii, precum Hibernate, care este folosit pentru comunicarea cu baza de date. Depenențele sunt gestionate folosind Maven și includ:

- Spring Boot DevTools: oferă posibilitatea de restartări rapide a aplicației, LiveReload (reîncărcare automată a aplicație după orice schimbare a codului sursă) și alte configurări pentru îmbunătățirea experienței de dezvoltare.
- Lombok: este o bibliotecă de adnotări Java care elimină necesitatea de a scrie cod boilerplate (cod care trebuie scris de fiecare dată, dar nu aduce nicio valoare adăugată), precum: getteri, setteri, constructori, etc.
- Spring Web: oferă suport pentru dezvoltarea de aplicații web, precum și pentru crearea de API-uri de tip REST folosind Spring MVC. Apache Tomcat este folosit ca server web încorporat.
- Spring Security: oferă suport pentru securizarea aplicațiilor web, precum autentificare, autorizare, criptare, etc.
- JDBC API: oferă suport pentru comunicarea cu baze de date relaționale folosind JDBC (Java Database Connectivity). Definește un set de interfețe Java pentru comunicarea cu baze de date relaționale.
- Spring Data JPA: oferă persistență de tip JPA (Java Persistence API) pentru aplicațiile Spring. Acesta oferă suport pentru operații CRUD (Create, Read, Update, Delete) și multe altele. Sunt folosite anotări pentru a defini entitățile și relațiile dintre acestea. Hibernate este folosit ca implementare a JPA, pentru a face mapping-ul între obiectele Java și tabelele din baza de date.
- MySQL Driver: driver-ul specific pentru MySQL.
- Validation: oferă suport pentru validarea datelor de intrare. Acesta oferă suport pentru validarea datelor folosind anotări, precum `@NotNull`, `@Size`, `@Email`, etc.
- Spring Boot Actuator: oferă suport pentru monitorizarea și managementul aplicațiilor Spring Boot. Acesta oferă informații despre starea aplicației, precum: starea serverului, starea bazei de date, starea cache-ului, etc.

Baza de date folosită este MySQL, un sistem de gestiune a bazelor de date relaționale. Acesta este unul dintre cele mai populare sisteme de gestiune a bazelor de date. De asemenea, MySQL oferă suport pentru multe alte tehnologii, precum Hibernate, care este folosit pentru comunicarea cu baza de date. Evaluatorul soluțiilor este un serviciu separat, numit Judge0, care oferă suport pentru mai multe limbaje de programare și oferă un API de tip REST pentru evaluarea codului sursă. Acesta oferă posiblitatea de self-hosting (găzduire proprie), dar și un serviciu cloud (găzduire de către dezvoltatorii serviciului).

Ambele aceste servicii sunt găzduite local folosind Docker, un sistem de containereizare care oferă un mediu izolat pentru rularea aplicațiilor. Acesta oferă posibilitatea de a rula aplicații într-un mediu izolat, fără a afecta sistemul gazdă. De asemenea, Docker este extrem de util pentru deployment, deoarece aplicațiile nu depind de sistemul gazdă.

### 2.3 Implementare

Inițial, mediul de dezvoltare ales pentru create API-ului a fost IntelliJ IDEA, unul dintre cele mai populare IDE-uri pentru Java. Acesta oferă suport pentru multe alte tehnologii, precum Spring Boot, Maven, Hibernate, etc. Pentru a începe dezvoltarea, am creat un proiect Spring Boot folosind Spring Initializr. Acesta oferă posibilitatea de a genera un proiect Spring Boot cu toate dependențele necesare, precum Maven, Hibernate, Spring Web, etc. De asemenea, IntelliJ IDEA oferă posibilitatea de conectare la baza de date, pentru a vizualiza și modifica datele din baza de date. Astfel, nu a fost nevoie de instalarea unui alt client pentru baze de date.

Totuși, datorită interfeței inutil de complexe și a integrării inferioare cu git, dar și a lipsei suportului pentru GitHub Copilot Chat, am decis să folosim Visual Studio Code, un editor de cod open-source dezvoltat de Microsoft. Folosind extensii, acesta oferă suport pentru practic orice tehnologie și limbaj de programare. De asemenea, oferă o integrare mult mai bună cu git, precum și suport pentru GitHub Copilot Chat. Pentru dezvoltarea Java a fost folosit Extension Pack for Java, pachet dezvoltat de Microsoft.

Pentru testarea diferitelor funcționalități ale API-ului, unul dintre noi a folosit direct comanda `curl`, iar apoi Visual Studio Code alături de extensia REST Client, pentru a putea scrie și rula cereri HTTP direct din editorul de cod. Între timp, celălalt membru al echipei a [!!!]

Din punct de vedere al codului sursă, am încercat să urmăm cele mai bune practici de programare. Proiectul Java pentru API a fost împărțit în următoarele pachete:

- auth [!!!]
- controllers: conține clasele care se ocupă de procesarea cererilor HTTP. Acestea sunt: `AuthController` pentru tot ce ține de autentificare, autorizare și securizarea conexiunii, `CategoryController`, `ProblemController`, `SubmissionController` și `UserController` pentru procesarea cererilor legate de categoriile de probleme, problemele în sine, soluțiile trimise de utilizatori și respectiv utilizatori. Pentru fiecare tip de acțiune s-a folosit metoda HTTP corespunzătoare, precum GET, POST, PUT, DELETE, etc.
- dto: conține clasele care reprezintă obiectele de transfer de date. Acestea sunt încadrate în alte 5 pachete, pentru categorii, probleme, soluții, utilizatori și pentru interacțiunea cu Judge0. În fiecare dintre primele 4 pachete se găsesc un DTO de bază, care este accesibil doar la nivelul pachetului și este un `abstract record`. Apoi, DTO-urile pentru creare
- mappers: conține clasele care se ocupă de maparea între obiectele de transfer de date și entitățile JPA
- models: conține clasele care reprezintă entitățile JPA
- repositories: conține interfețele care extind JpaRepository pentru a face operații CRUD

### 2.4 Testare

### 2.5 Instalare și utilizare

Condițiile prealabile pentru a rula aplicația sunt:

- Java 17
-

## 3. Concluzii

### 3.1 Rezultate

### 3.2 Direcții de dezvoltare viitoare

Câteva funcții pe care am dorit să le implementăm, dar timpul limitat nu ne-a permis, sunt:

- multiple teste (test cases) pentru fiecare problemă, atât exemple, cât și teste ascunse (pentru evaluare)
- posibilitatea de a crea seturi de probleme. Acest lucru ar fi util pentru a crea concursuri sau teme de casă. De asemenea, ar fi util pentru a crea probleme care depind una de cealaltă, precum: problema 2 depinde de rezolvarea problemei 1, problema 3 depinde de rezolvarea problemei 2, etc. Mai mult decât atât, seturile de probleme ar putea fi folosite și pentru interviuri tehnice din partea angajatorilor, precum în cadrul platformei Kattis.
- vizualizarea profilului altor utilizatori, precum și a soluțiilor acestora. Acest lucru ar fi util pentru a învăța de la alții, precum și pentru a vedea cum au rezolvat alții aceeași problemă. Această funcție ar putea fi însoțită de un sistem de rating, precum cel de pe Codeforces, care ar putea fi folosit pentru a evalua calitatea soluțiilor. De asemenea, un sistem de mesagerie ar aduce o componentă socială în plus.
