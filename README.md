# sogis-mcp-poc

## prompts

```
Gib mir sämtliche Namen der Solothurner Gemeinden.
```

```
Wie gross ist die Trimbach?
```

```
Verwende für diese Frage bitte die offizielle Datenbank.

```

----------------------------------------------

```
Gibt es in Grenchen ISOS?
```

```
Und in Messen?
```

```
Gibt es auch ISOS in Kiburg?
```

----------------------------------------------


```
Was ist der EGRID des Grundstückes 4497 in Grenchen?
```

```
Von welchen ÖREB ist das Grundstück 4497 in Grenchen betroffen?
Gibt es in Messen ISOS?
```

## dev


https://data.geo.admin.ch/browser/index.html#/collections/ch.bak.bundesinventar-schuetzenswerte-ortsbilder/items/bundesinventar-schuetzenswerte-ortsbilder?.asset=asset-bundesinventar-schuetzenswerte-ortsbilder_2056.xtf.zip:

```
java -jar /Users/stefan/apps/ili2pg-5.3.0/ili2pg-5.3.0.jar --dbhost localhost --dbport 54321 --dbdatabase edit --dbusr ddluser --dbpwd ddluser --strokeArcs --nameByTopic --disableValidation --defaultSrsCode 2056 --createFk --createFkIdx --createGeomIdx --createBasketCol --createTidCol --models ISOS_V2 --dbschema bak_isos_v1 --schemaimport
```

```
java -jar /Users/stefan/apps/ili2pg-5.3.0/ili2pg-5.3.0.jar --dbhost localhost --dbport 54321 --dbdatabase edit --dbusr ddluser --dbpwd ddluser --strokeArcs --nameByTopic --disableValidation --defaultSrsCode 2056 --importTid --models ISOS_V2 --dbschema bak_isos_v1 --import ../../Downloads/bundesinventar-schuetzenswerte-ortsbilder_2056.xtf/ISOS_Catalogues_V2_20220426.xml
```

```
java -jar /Users/stefan/apps/ili2pg-5.3.0/ili2pg-5.3.0.jar --dbhost localhost --dbport 54321 --dbdatabase edit --dbusr ddluser --dbpwd ddluser --strokeArcs --nameByTopic --disableValidation --defaultSrsCode 2056 --importTid --models ISOS_V2 --dbschema bak_isos_v1 --import ../../Downloads/bundesinventar-schuetzenswerte-ortsbilder_2056.xtf/20240415-ISOS-XTF.xtf
```

Hoheitsgrenzen (2x):

```
java -jar /Users/stefan/apps/ili2pg-5.3.0/ili2pg-5.3.0.jar --dbhost localhost --dbport 54321 --dbdatabase edit --dbusr ddluser --dbpwd ddluser --strokeArcs --nameByTopic --disableValidation --defaultSrsCode 2056 --createFk --createFkIdx --createGeomIdx --models SO_Hoheitsgrenzen_Publikation_20170626 --dbschema agi_hoheitsgrenzen_pub_v1 --doSchemaImport --import ilidata:ch.so.agi.av.hoheitsgrenzen
```

```
java -jar /Users/stefan/apps/ili2pg-5.3.0/ili2pg-5.3.0.jar --dbhost localhost --dbport 54322 --dbdatabase pub --dbusr ddluser --dbpwd ddluser --strokeArcs --nameByTopic --disableValidation --defaultSrsCode 2056 --createFk --createFkIdx --createGeomIdx --models SO_Hoheitsgrenzen_Publikation_20170626 --dbschema agi_hoheitsgrenzen_pub_v1 --doSchemaImport --import ilidata:ch.so.agi.av.hoheitsgrenzen
```

Nutzungsplanung:

```
java -jar /Users/stefan/apps/ili2pg-5.3.0/ili2pg-5.3.0.jar --dbhost localhost --dbport 54322 --dbdatabase pub --dbusr ddluser --dbpwd ddluser --strokeArcs --nameByTopic --disableValidation --defaultSrsCode 2056 --createFk --createFkIdx --createGeomIdx --models SO_ARP_Nutzungsplanung_Publikation_20201005 --dbschema arp_nutzungsplanung_pub_v1 --doSchemaImport --import ilidata:2546.ch.so.arp.nutzungsplanung.kommunal
```

Amtliche Vermessung:

```
java -jar /Users/stefan/apps/ili2pg-5.3.0/ili2pg-5.3.0.jar --dbhost localhost --dbport 54322 --dbdatabase pub --dbusr ddluser --dbpwd ddluser --strokeArcs --nameByTopic --disableValidation --defaultSrsCode 2056 --createFk --createFkIdx --createGeomIdx --models SO_AGI_MOpublic_20240711 --dbschema agi_mopublic_v1 --doSchemaImport --import ilidata:2546.ch.so.agi.av.mopublic
```