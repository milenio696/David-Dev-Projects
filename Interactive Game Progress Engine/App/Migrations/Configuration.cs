namespace BattleFightGrupo1.Migrations
{
    using BattleFightGrupo1.Models;
    using System;
    using System.Data.Entity;
    using System.Data.Entity.Migrations;
    using System.Linq;

    internal sealed class Configuration : DbMigrationsConfiguration<BattleFightGrupo1.Services.Service>
    {
        public Configuration()
        {
            AutomaticMigrationsEnabled = true;
        }

        protected override void Seed(BattleFightGrupo1.Services.Service context)
        {
            #region Seed_Equipos
            var equipos = new List<Equipo>
            {
                new Equipo { Id = 1, NombreEquipo = "Los Naranjas Poderosas", Jugador1 = "test3", Jugador2 = "test2", Jugador3 = "test4", Jugador4 = "robertj", Puntaje = 85, Categoria = "Beginner", CodigoEquipo = "B42524", FechaRegistro = new DateTime(2024, 12, 17) },
                new Equipo { Id = 2, NombreEquipo = "Guerreros de la Piña", Jugador1 = "eplane", Jugador2 = null, Jugador3 = null, Jugador4 = null, Puntaje = 90, Categoria = "Middle", CodigoEquipo = "M16124", FechaRegistro = new DateTime(2024, 12, 17) },
                new Equipo { Id = 3, NombreEquipo = "Los Duraznos Veloz", Jugador1 = "test3", Jugador2 = "janesmith", Jugador3 = "juanp", Jugador4 = "michaelbrown", Puntaje = 75, Categoria = "Expert", CodigoEquipo = "E41024", FechaRegistro = new DateTime(2024, 12, 17) },
                new Equipo { Id = 4, NombreEquipo = "Fuerza Fresa", Jugador1 = "luciag", Jugador2 = "robertj", Jugador3 = "johndoe", Jugador4 = "eplane", Puntaje = 92, Categoria = "Middle", CodigoEquipo = "M48324", FechaRegistro = new DateTime(2024, 12, 17) },
                new Equipo { Id = 5, NombreEquipo = "La Sandía Gigante", Jugador1 = "dabarca", Jugador2 = "robertj", Jugador3 = "johndoe", Jugador4 = "luciag", Puntaje = 80, Categoria = "Beginner", CodigoEquipo = "B45824", FechaRegistro = new DateTime(2024, 12, 18) },
                new Equipo { Id = 6, NombreEquipo = "Fresas Salvajes", Jugador1 = "ahidalgo", Jugador2 = "juanp", Jugador3 = "michaelbrown", Jugador4 = "eplane", Puntaje = 55, Categoria = "Middle", CodigoEquipo = "M44024", FechaRegistro = new DateTime(2024, 12, 18) },
                new Equipo { Id = 7, NombreEquipo = "Manzanas Ágiles", Jugador1 = "emilydavis", Jugador2 = "test4", Jugador3 = "johndoe", Jugador4 = "eplane", Puntaje = 96, Categoria = "Beginner", CodigoEquipo = "B42424", FechaRegistro = new DateTime(2024, 12, 18) },
                new Equipo { Id = 8, NombreEquipo = "Uvas Eléctricas", Jugador1 = "test2", Jugador2 = "johndoe", Jugador3 = "luciag", Jugador4 = "pedros", Puntaje = 99, Categoria = "Expert", CodigoEquipo = "E46024", FechaRegistro = new DateTime(2024, 12, 18) },
                new Equipo { Id = 9, NombreEquipo = "Melocotones Furiosos", Jugador1 = null, Jugador2 = "test2", Jugador3 = null, Jugador4 = null, Puntaje = 33, Categoria = "Middle", CodigoEquipo = "M17424", FechaRegistro = new DateTime(2024, 12, 18) },
                new Equipo { Id = 10, NombreEquipo = "Sandías Letales", Jugador1 = null, Jugador2 = "test2", Jugador3 = "eplane", Jugador4 = null, Puntaje = 33, Categoria = "Beginner", CodigoEquipo = "B21424", FechaRegistro = new DateTime(2024, 12, 19) },
                new Equipo { Id = 11, NombreEquipo = "Piñas Enérgicas", Jugador1 = null, Jugador2 = "test2", Jugador3 = "eplane", Jugador4 = null, Puntaje = 4, Categoria = "Middle", CodigoEquipo = "M29124", FechaRegistro = new DateTime(2024, 12, 19) },
                new Equipo { Id = 12, NombreEquipo = "Plátanos Poderosos", Jugador1 = "janesmith", Jugador2 = "johndoe", Jugador3 = "luciag", Jugador4 = null, Puntaje = 42, Categoria = "Beginner", CodigoEquipo = "B31224", FechaRegistro = new DateTime(2024, 12, 19) },
                new Equipo { Id = 13, NombreEquipo = "Cocos Salvajes", Jugador1 = "ahidalgo", Jugador2 = null, Jugador3 = "pedros", Jugador4 = "eplane", Puntaje = 77, Categoria = "Middle", CodigoEquipo = "M44124", FechaRegistro = new DateTime(2024, 12, 19) },
                new Equipo { Id = 14, NombreEquipo = "Peras Gigantes", Jugador1 = "test3", Jugador2 = "michaelbrown", Jugador3 = null, Jugador4 = null, Puntaje = 15, Categoria = "Expert", CodigoEquipo = "E41424", FechaRegistro = new DateTime(2024, 12, 19) },
                new Equipo { Id = 15, NombreEquipo = "Melones Mágicos", Jugador1 = "robertj", Jugador2 = "carlosm", Jugador3 = "janesmith", Jugador4 = "pedros", Puntaje = 89, Categoria = "Beginner", CodigoEquipo = "B41524", FechaRegistro = new DateTime(2024, 12, 20) },
                new Equipo { Id = 16, NombreEquipo = "Kiwis Letales", Jugador1 = "johndoe", Jugador2 = "eplane", Jugador3 = null, Jugador4 = "michaelbrown", Puntaje = 63, Categoria = "Middle", CodigoEquipo = "M41624", FechaRegistro = new DateTime(2024, 12, 20) },
                new Equipo { Id = 17, NombreEquipo = "Moras Furiosas", Jugador1 = "juanp", Jugador2 = "test4", Jugador3 = "luciag", Jugador4 = "janesmith", Puntaje = 2, Categoria = "Expert", CodigoEquipo = "E41724", FechaRegistro = new DateTime(2024, 12, 20) },
                new Equipo { Id = 18, NombreEquipo = "Guayabas Ágiles", Jugador1 = "test2", Jugador2 = null, Jugador3 = "ahidalgo", Jugador4 = "dabarca", Puntaje = 97, Categoria = "Beginner", CodigoEquipo = "B41824", FechaRegistro = new DateTime(2024, 12, 20) },
                new Equipo { Id = 19, NombreEquipo = "Papayas Rápidas", Jugador1 = "eplane", Jugador2 = "robertj", Jugador3 = null, Jugador4 = null, Puntaje = 58, Categoria = "Middle", CodigoEquipo = "M41924", FechaRegistro = new DateTime(2024, 12, 21) },
                new Equipo { Id = 20, NombreEquipo = "Cerezas Traviesas", Jugador1 = "luciag", Jugador2 = "pedros", Jugador3 = "test3", Jugador4 = null, Puntaje = 100, Categoria = "Expert", CodigoEquipo = "E42024", FechaRegistro = new DateTime(2024, 12, 21) },
                new Equipo { Id = 21, NombreEquipo = "Higos Eléctricos", Jugador1 = null, Jugador2 = "michaelbrown", Jugador3 = "test2", Jugador4 = "carlosm", Puntaje = 9, Categoria = "Beginner", CodigoEquipo = "B42124", FechaRegistro = new DateTime(2024, 12, 21) },
                new Equipo { Id = 22, NombreEquipo = "Frambuesas Brillantes", Jugador1 = "janesmith", Jugador2 = "juanp", Jugador3 = null, Jugador4 = "eplane", Puntaje = 44, Categoria = "Middle", CodigoEquipo = "M42224", FechaRegistro = new DateTime(2024, 12, 21) },
                new Equipo { Id = 23, NombreEquipo = "Mangos Velozes", Jugador1 = "test4", Jugador2 = "johndoe", Jugador3 = null, Jugador4 = null, Puntaje = 68, Categoria = "Expert", CodigoEquipo = "E42324", FechaRegistro = new DateTime(2024, 12, 22) },
                new Equipo { Id = 24, NombreEquipo = "Mandarinas Enérgicas", Jugador1 = "carlosm", Jugador2 = null, Jugador3 = "pedros", Jugador4 = "ahidalgo", Puntaje = 11, Categoria = "Beginner", CodigoEquipo = "B42424", FechaRegistro = new DateTime(2024, 12, 22) },
                new Equipo { Id = 25, NombreEquipo = "Limas Sutiles", Jugador1 = "juanp", Jugador2 = "janesmith", Jugador3 = "luciag", Jugador4 = null, Puntaje = 34, Categoria = "Middle", CodigoEquipo = "M42524", FechaRegistro = new DateTime(2024, 12, 22) },
                new Equipo { Id = 26, NombreEquipo = "Castañas Poderosas", Jugador1 = "eplane", Jugador2 = null, Jugador3 = "test2", Jugador4 = "michaelbrown", Puntaje = 73, Categoria = "Expert", CodigoEquipo = "E42624", FechaRegistro = new DateTime(2024, 12, 22) },
                new Equipo { Id = 27, NombreEquipo = "Granadas Imparables", Jugador1 = "dabarca", Jugador2 = "test3", Jugador3 = null, Jugador4 = "pedros", Puntaje = 66, Categoria = "Beginner", CodigoEquipo = "B42724", FechaRegistro = new DateTime(2024, 12, 23) },
                new Equipo { Id = 28, NombreEquipo = "Arándanos Descargados", Jugador1 = "luciag", Jugador2 = null, Jugador3 = "ahidalgo", Jugador4 = "carlosm", Puntaje = 25, Categoria = "Middle", CodigoEquipo = "M42824", FechaRegistro = new DateTime(2024, 12, 23) },
                new Equipo { Id = 29, NombreEquipo = "Limones Furiosos", Jugador1 = "johndoe", Jugador2 = "eplane", Jugador3 = "robertj", Jugador4 = null, Puntaje = 81, Categoria = "Expert", CodigoEquipo = "E42924", FechaRegistro = new DateTime(2024, 12, 23) },
                new Equipo { Id = 30, NombreEquipo = "Ciruelas Estelares", Jugador1 = null, Jugador2 = "janesmith", Jugador3 = "test4", Jugador4 = null, Puntaje = 5, Categoria = "Beginner", CodigoEquipo = "B43024", FechaRegistro = new DateTime(2024, 12, 23) }

            };

            // Add or update the equipos in the database
            foreach (var equi in equipos)
            {
                context.equipoDB.AddOrUpdate(e => e.Id, equi); // This will update if exists or add if not
            }
            #endregion

            #region Seed_Torneos
            var torneos = new List<Torneo>
            {
                new Torneo { CodigoTorneo = "T001", FechaTorneo = DateTime.Now, PremioMonetario = 100000, Categoria = "Beginner" },
                new Torneo { CodigoTorneo = "T002", FechaTorneo = DateTime.Now, PremioMonetario = 150000, Categoria = "Beginner" },
                new Torneo { CodigoTorneo = "T003", FechaTorneo = DateTime.Now, PremioMonetario = 250000, Categoria = "Beginner" },
                new Torneo { CodigoTorneo = "T004", FechaTorneo = DateTime.Now, PremioMonetario = 100000, Categoria = "Beginner" },
                new Torneo { CodigoTorneo = "T005", FechaTorneo = DateTime.Now, PremioMonetario = 150000, Categoria = "Beginner" },
                new Torneo { CodigoTorneo = "T006", FechaTorneo = DateTime.Now, PremioMonetario = 100000, Categoria = "Middle" },
                new Torneo { CodigoTorneo = "T007", FechaTorneo = DateTime.Now, PremioMonetario = 150000, Categoria = "Middle" },
                new Torneo { CodigoTorneo = "T008", FechaTorneo = DateTime.Now, PremioMonetario = 250000, Categoria = "Middle" },
                new Torneo { CodigoTorneo = "T009", FechaTorneo = DateTime.Now, PremioMonetario = 100000, Categoria = "Middle" },
                new Torneo { CodigoTorneo = "T010", FechaTorneo = DateTime.Now, PremioMonetario = 150000, Categoria = "Middle" },
                new Torneo { CodigoTorneo = "T011", FechaTorneo = DateTime.Now, PremioMonetario = 100000, Categoria = "Expert" },
                new Torneo { CodigoTorneo = "T012", FechaTorneo = DateTime.Now, PremioMonetario = 150000, Categoria = "Expert" },
                new Torneo { CodigoTorneo = "T013", FechaTorneo = DateTime.Now, PremioMonetario = 250000, Categoria = "Expert" },
                new Torneo { CodigoTorneo = "T014", FechaTorneo = DateTime.Now, PremioMonetario = 100000, Categoria = "Expert" },
                new Torneo { CodigoTorneo = "T015", FechaTorneo = DateTime.Now, PremioMonetario = 150000, Categoria = "Expert" },
                new Torneo { CodigoTorneo = "T016", FechaTorneo = DateTime.Now, PremioMonetario = 100000, Categoria = "Beginner" },
                new Torneo { CodigoTorneo = "T017", FechaTorneo = DateTime.Now, PremioMonetario = 150000, Categoria = "Middle" },
                new Torneo { CodigoTorneo = "T018", FechaTorneo = DateTime.Now, PremioMonetario = 250000, Categoria = "Expert" }

            };
            foreach (var tor in torneos)
            {
                context.torneoDB.AddOrUpdate(e => e.Id, tor);
            }
            #endregion

            #region Seed_Usuarios
            var usuarios = new List<Usuario> {
                new Usuario { Id = 1, Nombre = "test 1", Cedula = "1234567890", Genero = "Masculino", FechaRegistro = DateTime.Now, Estado = "Activo", UsuarioLogin = "test1", Contrasena = "test" },
                new Usuario { Id = 2, Nombre = "test 2", Cedula = "1234565890", Genero = "Femenino", FechaRegistro = DateTime.Now, Estado = "Activo", UsuarioLogin = "test2", Contrasena = "test" },
                new Usuario { Id = 3,  Nombre = "test 3", Cedula = "1234566890", Genero = "Masculino", FechaRegistro = DateTime.Now, Estado = "Activo", UsuarioLogin = "test3", Contrasena = "test" },
                new Usuario { Id = 4, Nombre = "test 4", Cedula = "1234568890", Genero = "Femenino", FechaRegistro = DateTime.Now, Estado = "Activo", UsuarioLogin = "test4", Contrasena = "test" },
                new Usuario { Id = 5, Nombre = "eduardo planelles", Cedula = "9999999999", Genero = "Masculino", FechaRegistro = DateTime.Now, Estado = "Inactivo", UsuarioLogin = "eplane", Contrasena = "test" },
                new Usuario { Id = 6, Nombre = "david abarca", Cedula = "9999999999", Genero = "Masculino", FechaRegistro = DateTime.Now, Estado = "Inactivo", UsuarioLogin = "dabarca", Contrasena = "test" },
                new Usuario { Id = 7, Nombre = "geysell rivas", Cedula = "9999999999", Genero = "Femenino", FechaRegistro = DateTime.Now, Estado = "Activo", UsuarioLogin = "grivas", Contrasena = "test" },
                new Usuario { Id = 8, Nombre = "allan hidalgo", Cedula = "9999999999", Genero = "Masculino", FechaRegistro = DateTime.Now, Estado = "Activo", UsuarioLogin = "ahidalgo", Contrasena = "test" },
                new Usuario { Id = 9, Nombre = "John Doe", Cedula = "1234567890", Genero = "Masculino", FechaRegistro = DateTime.Now, Estado = "Activo", UsuarioLogin = "johndoe", Contrasena = "pass123" },
                new Usuario { Id = 10, Nombre = "Jane Smith", Cedula = "9876543210", Genero = "Femenino", FechaRegistro = DateTime.Now, Estado = "Activo", UsuarioLogin = "janesmith", Contrasena = "password456" },
                new Usuario { Id = 11, Nombre = "Robert Johnson", Cedula = "1122334455", Genero = "Masculino", FechaRegistro = DateTime.Now, Estado = "Inactivo", UsuarioLogin = "robertj", Contrasena = "password789" },
                new Usuario { Id = 12, Nombre = "Emily Davis", Cedula = "2233445566", Genero = "Femenino", FechaRegistro = DateTime.Now, Estado = "Activo", UsuarioLogin = "emilydavis", Contrasena = "password321" },
                new Usuario { Id = 13, Nombre = "Michael Brown", Cedula = "3344556677", Genero = "Masculino", FechaRegistro = DateTime.Now, Estado = "Activo", UsuarioLogin = "michaelbrown", Contrasena = "password654" },
                new Usuario { Id = 14, Nombre = "Carlos Martínez", Cedula = "1234567891", Genero = "Masculino", FechaRegistro = DateTime.Now, Estado = "Activo", UsuarioLogin = "carlosm", Contrasena = "pass987" },
                new Usuario { Id = 15, Nombre = "Lucía Gómez", Cedula = "9876543211", Genero = "Femenino", FechaRegistro = DateTime.Now, Estado = "Activo", UsuarioLogin = "luciag", Contrasena = "password555" },
                new Usuario { Id = 16, Nombre = "Juan Pérez", Cedula = "1122334456", Genero = "Masculino", FechaRegistro = DateTime.Now, Estado = "Activo", UsuarioLogin = "juanp", Contrasena = "test123" },
                new Usuario { Id = 17, Nombre = "María López", Cedula = "2233445567", Genero = "Femenino", FechaRegistro = DateTime.Now, Estado = "Activo", UsuarioLogin = "marial", Contrasena = "secure321" },
                new Usuario { Id = 18, Nombre = "Pedro Sánchez", Cedula = "3344556678", Genero = "Masculino", FechaRegistro = DateTime.Now, Estado = "Activo", UsuarioLogin = "pedros", Contrasena = "test654" },
                new Usuario { Id = 19, Nombre = "Isabel Rodríguez", Cedula = "4455667789", Genero = "Femenino", FechaRegistro = DateTime.Now, Estado = "Activo", UsuarioLogin = "isabelr", Contrasena = "password777" },
                new Usuario { Id = 20, Nombre = "Antonio García", Cedula = "5566778890", Genero = "Masculino", FechaRegistro = DateTime.Now, Estado = "Activo", UsuarioLogin = "antoniog", Contrasena = "secret987" },
                new Usuario { Id = 21, Nombre = "Ana Fernández", Cedula = "6677889901", Genero = "Femenino", FechaRegistro = DateTime.Now, Estado = "Activo", UsuarioLogin = "anaf", Contrasena = "abc123" },
                new Usuario { Id = 22, Nombre = "José Martínez", Cedula = "7788990012", Genero = "Masculino", FechaRegistro = DateTime.Now, Estado = "Activo", UsuarioLogin = "josem", Contrasena = "secret654" },
                new Usuario { Id = 23, Nombre = "Laura González", Cedula = "8899001123", Genero = "Femenino", FechaRegistro = DateTime.Now, Estado = "Activo", UsuarioLogin = "laurag", Contrasena = "password888" },
                new Usuario { Id = 24, Nombre = "Carlos López", Cedula = "9900112234", Genero = "Masculino", FechaRegistro = DateTime.Now, Estado = "Activo", UsuarioLogin = "carlosl", Contrasena = "pass456" },
                new Usuario { Id = 25, Nombre = "Elena García", Cedula = "1011123345", Genero = "Femenino", FechaRegistro = DateTime.Now, Estado = "Activo", UsuarioLogin = "elenag", Contrasena = "mypassword" },
                new Usuario { Id = 26, Nombre = "Ricardo Pérez", Cedula = "1122334457", Genero = "Masculino", FechaRegistro = DateTime.Now, Estado = "Activo", UsuarioLogin = "ricardop", Contrasena = "mypassword456" },
                new Usuario { Id = 27, Nombre = "Sofía Díaz", Cedula = "2233445568", Genero = "Femenino", FechaRegistro = DateTime.Now, Estado = "Activo", UsuarioLogin = "sofiad", Contrasena = "pass987654" },
                new Usuario { Id = 28, Nombre = "David López", Cedula = "3344556679", Genero = "Masculino", FechaRegistro = DateTime.Now, Estado = "Activo", UsuarioLogin = "davidl", Contrasena = "secure321" },
                new Usuario { Id = 29, Nombre = "Carmen Pérez", Cedula = "4455667790", Genero = "Femenino", FechaRegistro = DateTime.Now, Estado = "Activo", UsuarioLogin = "carmenp", Contrasena = "secure987" },
                new Usuario { Id = 30, Nombre = "Alberto Torres", Cedula = "5566778891", Genero = "Masculino", FechaRegistro = DateTime.Now, Estado = "Activo", UsuarioLogin = "albertt", Contrasena = "password333" },
                new Usuario { Id = 31, Nombre = "Beatriz Ruiz", Cedula = "6677889902", Genero = "Femenino", FechaRegistro = DateTime.Now, Estado = "Activo", UsuarioLogin = "beatrizr", Contrasena = "mypassword123" },
                new Usuario { Id = 32, Nombre = "Héctor Martínez", Cedula = "7788990013", Genero = "Masculino", FechaRegistro = DateTime.Now, Estado = "Activo", UsuarioLogin = "hectorm", Contrasena = "mypassword789" },
                new Usuario { Id = 33, Nombre = "Marta Gómez", Cedula = "8899001124", Genero = "Femenino", FechaRegistro = DateTime.Now, Estado = "Activo", UsuarioLogin = "martag", Contrasena = "password111" }
            };
            foreach (var usu in usuarios)
            {
                context.usuarioDB.AddOrUpdate(e => e.Id, usu);
            }
            #endregion
        }
    }
}
