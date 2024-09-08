Clase interesante : StudentSerializer -> imi face un string care sa fie html si apoi concatenat cu alte string-uri sa trimita ca raspuns un intreg fisier html care sa arate studentii in ordine.














Deci în cadrul aplicației JEE TEST, care e de tip web application, vei avea un  artefact de tip WAR(web archive) care va fi deployed(incarcat) automat prin plugin-ul cargo (care și acesta e un artefact folosit în proiectul nostru) pe serverul glassfish, war-ul facandu-se după ce ai dat build si compile prin maven a aplicatiei.

Aplicatia este o aplicatie simpla cu arhitectura JEE, care utilizeaza bean-uri. Aceasta are rolul de a arata conceptul de servleti si pagini jsp. In aceasta se afla un formular care salveaza un student intr-un fisier xml si cu ajutorul unui servlet se va citi din acel fisier si se va afisa pe o pagina jsp, respectiv va putea primi update/delete.


Adăugați posibilitatea de a introduce mai multe seturi de informații despre studenţi şi, de
asemenea, de a căuta un anumit student după un criteriu stabilit (de ex. după nume sau
prenume), cu următorii pași:
1. Căutarea studentului în fișierul XML care conţine colecţia de studenţi
2. Încărcarea datelor care coincid rezultatului căutării în memorie şi afişarea lor ca
   răspuns al unui servlet sau într-o pagină JSP (la alegere).

