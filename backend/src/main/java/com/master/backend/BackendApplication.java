package com.master.backend;

import com.master.backend.domain.Criteria;
import com.master.backend.domain.Permission;
import com.master.backend.model.*;
import com.master.backend.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication()
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
		System.out.println("Lejla je zakon");
	}


	private String hashPassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String hashPassword = encoder.encode(password);
		return hashPassword;
	}

	@Bean
	public CommandLineRunner addData(FirstTabRepository probaRepository, CriteriaRepository criteriaRepository, SupplierRepository supplierRepository, GradeRepository gradeRepository, UserRepository userRepository, ContractRepository contractRepository, RoleRepository roleRepository, PermissionRepository permissionRepository) {
		return(args) -> {
			FirstTabEntity firstTabEntity = new FirstTabEntity();
			firstTabEntity.setName("Lejla");
			probaRepository.save(firstTabEntity);

			CriteriaEntity criteriaEntity1 = new CriteriaEntity("Brzina dostave",2.55);
			criteriaRepository.save(criteriaEntity1);
			CriteriaEntity criteriaEntity2 = new CriteriaEntity("Kvalitet robe",4.55);
			criteriaRepository.save(criteriaEntity2);
			CriteriaEntity criteriaEntity3 = new CriteriaEntity("Cijena",3.55);
			criteriaRepository.save(criteriaEntity3);

			SupplierEntity supplierEntity1 = new SupplierEntity("Kancelarijski materijal d.o.o.","Vršimo dostavu svih kancelarijskih potrošnih materijala","1234567890");
			supplierRepository.save(supplierEntity1);
			SupplierEntity supplierEntity2 = new SupplierEntity("Namjestaj d.o.o.","Opremanje svih kancelarijskih prostora i osposobljavanje za rad","1114567890");
			supplierRepository.save(supplierEntity2);
			SupplierEntity supplierEntity3 = new SupplierEntity("Higijena d.o.o.","Vršimo dostavu svih higijenskih materijala za kancelarijske prostore","2224567890");
			supplierRepository.save(supplierEntity3);
			//SupplierEntity supplierEntity4 = new SupplierEntity("Transport d.o.o.","Vršimo dostavu svih dijelova za službena vozila i bavimo se održavanjem i zamijenom u slučaju kvara","2224567890");
			//supplierRepository.save(supplierEntity4);

			GradeEntity gradeEntity1 = new GradeEntity(1,2020, criteriaEntity1, supplierEntity1);
			gradeRepository.save(gradeEntity1);
			GradeEntity gradeEntity5 = new GradeEntity(2,2021, criteriaEntity1, supplierEntity1);
			gradeRepository.save(gradeEntity5);
			GradeEntity gradeEntity6 = new GradeEntity(3,2022, criteriaEntity1, supplierEntity1);
			gradeRepository.save(gradeEntity6);
			GradeEntity gradeEntity2 = new GradeEntity(2,2021, criteriaEntity2, supplierEntity2);
			gradeRepository.save(gradeEntity2);
			GradeEntity gradeEntity21 = new GradeEntity(4,2022, criteriaEntity2, supplierEntity2);
			gradeRepository.save(gradeEntity21);
			GradeEntity gradeEntity22 = new GradeEntity(5,2020, criteriaEntity2, supplierEntity2);
			gradeRepository.save(gradeEntity22);
			GradeEntity gradeEntity3 = new GradeEntity(3,2020, criteriaEntity3, supplierEntity3);
			gradeRepository.save(gradeEntity3);
			GradeEntity gradeEntity31 = new GradeEntity(2,2021, criteriaEntity3, supplierEntity3);
			gradeRepository.save(gradeEntity31);
			GradeEntity gradeEntity32 = new GradeEntity(4,2022, criteriaEntity3, supplierEntity3);
			gradeRepository.save(gradeEntity32);


			PermissionEntity pristupAdminPanelu = new PermissionEntity("Pristup admin panelu", "Korisnici sa ovom permisijom mogu pristupati admin panelu");
			permissionRepository.save(pristupAdminPanelu);
			PermissionEntity pristupSupplierPanelu = new PermissionEntity("Pristup panelu supplier menadzmenta", "Korisnici sa ovom permisijom mogu pristupati supplier panelu");
			permissionRepository.save(pristupSupplierPanelu);
			PermissionEntity pristupAccessPanelu = new PermissionEntity("Pristup panelu access menadzmenta", "Korisnici sa ovom permisijom mogu pristupati access panelu");
			permissionRepository.save(pristupAccessPanelu);
			PermissionEntity permissionEntity1 = new PermissionEntity("Dodavanje nove uloge", "Korisnici sa ovom permisijom mogu dodati novu ulogu u sistem");
			permissionRepository.save(permissionEntity1);
			PermissionEntity permissionEntity2 = new PermissionEntity("Dodavanje nove permisije postojecim ulogama", "Korisnici sa ovom permisijom mogu dodati novu permisiju za određenu ulogu u sistem");
			permissionRepository.save(permissionEntity2); //update uloge
			PermissionEntity permissionEntity3 = new PermissionEntity("Dodavanje novog uposlenika", "Korisnici sa ovom permisijom mogu dodati nove korisnike sa odgovarajućim permisijama u sistem");
			permissionRepository.save(permissionEntity3);
			PermissionEntity permissionEntity4 = new PermissionEntity("Dodavanje nove uloge vec postojecem uposleniku", "Korisnici sa ovom permisijom mogu dodati novu ulogu već postojećem uposleniku te tako proširiti njegov pristup sistemu");
			permissionRepository.save(permissionEntity4); //update user
			PermissionEntity permissionEntity5 = new PermissionEntity("Dodavanje novog ugovora", "Korisnici sa ovom permisijom mogu sklopiti novi ugovor sa dobavljačima i dodati ga u sistem");
			permissionRepository.save(permissionEntity5);
			PermissionEntity permissionEntity6 = new PermissionEntity("Brisanje ugovora", "Korisnici sa ovom permisijom mogu obrisati postojeći ugovor sa dobavljačima što bi ujedno značilo i prekid saradnje");
			permissionRepository.save(permissionEntity6);
			PermissionEntity permissionEntity7 = new PermissionEntity("Pregled svih ugovora", "Korisnici sa ovom permisijom mogu izvršiti pregled svih postojećih ugovora");
			permissionRepository.save(permissionEntity7);
			PermissionEntity permissionEntity8 = new PermissionEntity("Pregled ugovora po rednom broju", "Korisnici sa ovom permisijom mogu pregledati ugovore po rednom broju u bazi podataka");
			permissionRepository.save(permissionEntity8);
			PermissionEntity permissionEntity9 = new PermissionEntity("Dodavanje novog kriterija u sistem", "Korisnici sa ovom permisijom mogu dodati novi kriterij na osnovu kojeg će se vršiti ocjenjivanje");
			permissionRepository.save(permissionEntity9);
			PermissionEntity permissionEntity10 = new PermissionEntity("Pregled svih kriterija", "Korisnici sa ovom permisijom mogu pregledati sve postojeće kriterije na osnovu kojih se vrši ocjenjivanje");
			permissionRepository.save(permissionEntity10);
			PermissionEntity permissionEntity11 = new PermissionEntity("Brisanje kriterija", "Korisnici sa ovom permisijom mogu obrisati kriterij na osnovu kojeg se vršilo ocjenjivanje");
			permissionRepository.save(permissionEntity11);
			PermissionEntity permissionEntity12 = new PermissionEntity("Pregled svih ocjena u sistemu", "Korisnici sa ovom permisijom mogu pregledati ocjene kojim je moguće izvršiti ocjenjivanje dobavljača");
			permissionRepository.save(permissionEntity12);
			PermissionEntity permissionEntity13 = new PermissionEntity("Prikaz konacne ocjene za dobavljaca", "Korisnici sa ovom permisijom mogu vidjeti prosječnu ocjenu za odabranog dobavljača");
			permissionRepository.save(permissionEntity13);
			PermissionEntity permissionEntity14 = new PermissionEntity("Prikaz statistike", "Korisnici sa ovom permisijom mogu pristupiti statistici baziranoj na ocjenama svih dobavljača");
			permissionRepository.save(permissionEntity14);
			PermissionEntity permissionEntity15 = new PermissionEntity("Dodavanje nove permisije", "Korisnici sa ovom permisijom mogu dodati novu permisiju u sistem");
			permissionRepository.save(permissionEntity15);
			PermissionEntity permissionEntity16 = new PermissionEntity("Brisanje postojece permisije", "Korisnici sa ovom permisijom mogu obrisati postojeću permisiju");
			permissionRepository.save(permissionEntity16);
			PermissionEntity permissionEntity17 = new PermissionEntity("Pregled svih permisija u sistemu", "Korisnici sa ovom permisijom mogu izvršiti pregled svih postojećih permisija");
			permissionRepository.save(permissionEntity17);
			PermissionEntity permissionEntity18 = new PermissionEntity("Pregled permisije po rednom broju", "Korisnici sa ovom permisijom mogu pregledati permisije po rednom broju u bazi podataka");
			permissionRepository.save(permissionEntity18);
			PermissionEntity permissionEntity19 = new PermissionEntity("Brisanje postojece uloge", "Korisnici sa ovom permisijom mogu obrisati postojeću ulogu");
			permissionRepository.save(permissionEntity19);
			PermissionEntity permissionEntity20 = new PermissionEntity("Pregled svih uloga u sistemu", "Korisnici sa ovom permisijom mogu izvršiti pregled svih postojećih uloga");
			permissionRepository.save(permissionEntity20);
			PermissionEntity permissionEntity21 = new PermissionEntity("Pregled uloga po rednom broju", "Korisnici sa ovom permisijom mogu pregledati uloge po rednom broju u bazi podataka");
			permissionRepository.save(permissionEntity21);
			PermissionEntity permissionEntity22 = new PermissionEntity("Update postojecih uloga", "Korisnici sa ovom permisijom mogu postojeće uloge urediti ili modifikovati");
			permissionRepository.save(permissionEntity22);
			PermissionEntity permissionEntity23 = new PermissionEntity("Brisanje postojeceg dobavljaca", "Korisnici sa ovom permisijom mogu obrisati postojećeg dobavljača");
			permissionRepository.save(permissionEntity23);
			PermissionEntity permissionEntity24 = new PermissionEntity("Pregled svih dobavljaca u sistemu", "Korisnici sa ovom permisijom mogu izvršiti pregled svih postojećih dobavljača");
			permissionRepository.save(permissionEntity24);
			PermissionEntity permissionEntity25 = new PermissionEntity("Pregled dobavljaca po rednom broju", "Korisnici sa ovom permisijom mogu pregledati dobavljače po rednom broju u bazi podataka");
			permissionRepository.save(permissionEntity25);
			PermissionEntity permissionEntity26 = new PermissionEntity("Update postojecih dobavljaca", "Korisnici sa ovom permisijom mogu postojeće dobavljače urediti ili modifikovati");
			permissionRepository.save(permissionEntity26);
			PermissionEntity permissionEntity27 = new PermissionEntity("Dodavanje novog dobavljaca", "Korisnici sa ovom permisijom mogu dodati novog dobavljača u sistem");
			permissionRepository.save(permissionEntity27);
			PermissionEntity permissionEntity28 = new PermissionEntity("Brisanje postojeceg uposlenika", "Korisnici sa ovom permisijom mogu obrisati postojećeg uposlenika");
			permissionRepository.save(permissionEntity28);
			PermissionEntity permissionEntity29 = new PermissionEntity("Pregled svih uposlenika u sistemu", "Korisnici sa ovom permisijom mogu izvršiti pregled svih postojećih uposlenika");
			permissionRepository.save(permissionEntity29);
			PermissionEntity permissionEntity30 = new PermissionEntity("Pregled uposlenika po rednom broju", "Korisnici sa ovom permisijom mogu pregledati uposlenike po rednom broju u bazi podataka");
			permissionRepository.save(permissionEntity30);
			PermissionEntity permissionEntity31 = new PermissionEntity("Update postojecih uposlenika", "Korisnici sa ovom permisijom mogu postojeće uposlenike urediti ili modifikovati");
			permissionRepository.save(permissionEntity31);
			PermissionEntity permissionEntity32 = new PermissionEntity("Pregled kriterija po rednom broju", "Korisnici sa ovom permisijom pregledati kriterij na osnovu rednog broju u sistemu");
			permissionRepository.save(permissionEntity32);
			PermissionEntity permissionEntity33 = new PermissionEntity("Dodavanje nove ocjene u sistem", "Korisnici sa ovom permisijom mogu dodavati nove ocjene u sistem, odnosno ocjenjivati dobavljače");
			permissionRepository.save(permissionEntity33);

			RoleEntity roleEntity1 = new RoleEntity("Menadžer za procese upravljanja pristupom", "Prava pristupa svim procesima upravljanja pristupom sistemu", List.of(pristupAccessPanelu ,permissionEntity1, permissionEntity2, permissionEntity3, permissionEntity4, permissionEntity28, permissionEntity29, permissionEntity30, permissionEntity31, permissionEntity19, permissionEntity20, permissionEntity21, permissionEntity22, permissionEntity15, permissionEntity16, permissionEntity17, permissionEntity18));
			roleRepository.save(roleEntity1);
			RoleEntity roleEntity2 = new RoleEntity("Menadžer za procese upravljanja dostavljačima", "Prava pristupa svim procesima upravljanja dobavljačima u sistemu", List.of(pristupSupplierPanelu, permissionEntity5, permissionEntity6, permissionEntity7, permissionEntity8, permissionEntity9, permissionEntity10, permissionEntity11, permissionEntity12, permissionEntity13, permissionEntity14, permissionEntity23, permissionEntity24, permissionEntity25, permissionEntity26, permissionEntity27, permissionEntity32, permissionEntity33, permissionEntity29));
			roleRepository.save(roleEntity2);
			RoleEntity roleEntity3 = new RoleEntity("Administrator", "Prava pristupa svim procesima u sistemu", List.of(pristupAdminPanelu, permissionEntity7, permissionEntity8, permissionEntity10, permissionEntity12, permissionEntity13, permissionEntity14, permissionEntity17, permissionEntity18, permissionEntity20, permissionEntity21, permissionEntity24, permissionEntity25, permissionEntity29, permissionEntity30, permissionEntity32));
			roleRepository.save(roleEntity3);
			RoleEntity roleEntity4 = new RoleEntity("Uloga ovlaštena za pregled dobavljača i ugovora", "Prava pristupa isključivo pregledu dobavljača i ugovora", new ArrayList<>());
			roleRepository.save(roleEntity4);

			UserEntity userEntity1 = new UserEntity("Dolores", "Jureta", "djureta1@etf.unsa.ba", "djureta1", hashPassword("password123"), List.of(roleEntity1));
			userRepository.save(userEntity1);
			UserEntity userEntity2 = new UserEntity("Jane", "Doe", "janedoe@mail.ba", "janedoe", hashPassword("password123"), List.of(roleEntity2));
			userRepository.save(userEntity2);
			UserEntity userEntity3 = new UserEntity("John", "Doe", "johndoe@mail.ba", "johndoe", hashPassword("password123"), List.of(roleEntity3));
			userRepository.save(userEntity3);
			UserEntity userEntity4 = new UserEntity("James", "Doe", "jamesdoe@email.ba", "jamesdoe", hashPassword("password123"), List.of(roleEntity2));
			userRepository.save(userEntity4);

			ContractEntity contractEntity1 = new ContractEntity(new Date(), new Date(), userEntity1, supplierEntity1);
			contractRepository.save(contractEntity1);
			ContractEntity contractEntity2 = new ContractEntity(new Date(), new Date(), userEntity2, supplierEntity2);
			contractRepository.save(contractEntity2);
			ContractEntity contractEntity3 = new ContractEntity(new Date(), new Date(), userEntity3, supplierEntity3);
			contractRepository.save(contractEntity3);
		};
	}
}
