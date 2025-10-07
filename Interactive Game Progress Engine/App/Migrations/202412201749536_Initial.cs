namespace BattleFightGrupo1.Migrations
{
    using System;
    using System.Data.Entity.Migrations;

    public partial class Initial : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.Equipoes",
                c => new
                {
                    Id = c.Int(nullable: false, identity: true),
                    NombreEquipo = c.String(),
                    Jugador1 = c.String(),
                    Jugador2 = c.String(),
                    Jugador3 = c.String(),
                    Jugador4 = c.String(),
                    Puntaje = c.Int(nullable: false),
                    Categoria = c.String(),
                    CodigoEquipo = c.String(),
                    FechaRegistro = c.DateTime(nullable: false),
                })
                .PrimaryKey(t => t.Id);

            CreateTable(
                "dbo.TorneoEquipoes",
                c => new
                {
                    TorneoId = c.Int(nullable: false),
                    EquipoId = c.Int(nullable: false),
                    Fecha = c.DateTime(nullable: false),
                    Equipo_Id = c.Int(),
                })
                .PrimaryKey(t => new { t.TorneoId, t.EquipoId })
                .ForeignKey("dbo.Equipoes", t => t.EquipoId)
                .ForeignKey("dbo.Torneos", t => t.TorneoId)
                .ForeignKey("dbo.Equipoes", t => t.Equipo_Id)
                .Index(t => t.TorneoId)
                .Index(t => t.EquipoId)
                .Index(t => t.Equipo_Id);

            CreateTable(
                "dbo.Torneos",
                c => new
                {
                    Id = c.Int(nullable: false, identity: true),
                    CodigoTorneo = c.String(),
                    FechaTorneo = c.DateTime(nullable: false),
                    PremioMonetario = c.Double(nullable: false),
                    Categoria = c.String(),
                    Ganador = c.String(),
                })
                .PrimaryKey(t => t.Id);

            CreateTable(
                "dbo.Usuarios",
                c => new
                {
                    Id = c.Int(nullable: false, identity: true),
                    Nombre = c.String(),
                    Cedula = c.String(),
                    Genero = c.String(),
                    FechaRegistro = c.DateTime(nullable: false),
                    Estado = c.String(),
                    UsuarioLogin = c.String(),
                    Contrasena = c.String(),
                })
                .PrimaryKey(t => t.Id);

        }

        public override void Down()
        {
            DropForeignKey("dbo.TorneoEquipoes", "Equipo_Id", "dbo.Equipoes");
            DropForeignKey("dbo.TorneoEquipoes", "TorneoId", "dbo.Torneos");
            DropForeignKey("dbo.TorneoEquipoes", "EquipoId", "dbo.Equipoes");
            DropIndex("dbo.TorneoEquipoes", new[] { "Equipo_Id" });
            DropIndex("dbo.TorneoEquipoes", new[] { "EquipoId" });
            DropIndex("dbo.TorneoEquipoes", new[] { "TorneoId" });
            DropTable("dbo.Usuarios");
            DropTable("dbo.Torneos");
            DropTable("dbo.TorneoEquipoes");
            DropTable("dbo.Equipoes");
        }
    }
}
