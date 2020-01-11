DESTI_CLASSES = /bin/
CODI_FONT = /src/

.PHONY: all
all: jar

$(DESTI_CLASSES):
	mkdir -p $(DESTI_CLASSES)

	

.PHONY: class
class: 
	javac -d bin Domini/*.java main_domain/*.java Persistencia/*.java Presentacio/*.java Drivers/*.java
		
run: 
	java -classpath ./bin/ Presentacio.PresentacioPrincipal
	
driver_controller:
	java -classpath ./bin/ Drivers.DriverController
	
driver_compressfile: 
	java -classpath ./bin/ Drivers.DriverCompressFiles	
	
main:
	java -classpath ./bin/ main_domain.main



.PHONY: help
help:
	@echo "Opcions:"
	@echo "class: Compila el codi al /bin"
	@echo "driver_controller: executa el driver controlador de domini"
	@echo "driver_compressfile: executa el driver de classe compressfile"
	@echo "main_domain : executa el main desde domini sense classe de presentació."
	@echo "run: Executa el codi"

.PHONY: clean
clean:
	rm -rf bin
