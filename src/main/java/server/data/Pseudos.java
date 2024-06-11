package server.data;

import utils.Mathf;

public class Pseudos {

    public static final String[] DATAS = {
        "Blaze", "Bolt", "Spark", "Flare", "Zap", "Flash", "Storm", "Inferno", "Blitz", "Rift",
        "Fury", "Vortex", "Glitch", "Byte", "Jolt", "Surge", "Quake", "Echo", "Nova", "Frost",
        "Raven", "Shadow", "Phantom", "Specter", "Wraith", "Abyss", "Gloom", "Viper", "Venom",
        "Striker", "Blaster", "Crusher", "Sniper", "Hunter", "Marauder", "Raider", "Warrior",
        "Titan", "Juggernaut", "Rogue", "Merc", "Havoc", "Wrath", "Saber", "Scorpion", "Blade",
        "Dagger", "Shuriken", "Bullet", "Arrow", "Raptor", "Talon", "Claw", "Tusk", "Fang",
        "Hornet", "Cobra", "Python", "Anaconda", "Falcon", "Eagle", "Hawk", "Condor", "Vulture",
        "Prowler", "Stalker", "Predator", "Reaper", "Ghost", "Specter", "Wraith", "Ghoul",
        "Banshee", "Shade", "Spectral", "Omen", "Oracle", "Mystic", "Sphinx", "Mirage", "Illusion",
        "Echo", "Eclipse", "Nova", "Comet", "Meteor", "Astro", "Star", "Cosmo", "Orbit",
        "Galaxy", "Nebula", "Quasar", "Pulsar", "Blackhole", "Singularity", "Quantum", "Atom",
        "Neutron", "Proton", "Electron", "Photon", "Gravity", "Warp", "Vortex", "Void", "Aether",
        "Plasma", "Fusion", "Fission", "Energy", "Force", "Power", "Spark", "Volt", "Amp",
        "Watt", "Joule", "Tesla", "Einstein", "Hawking", "Curie", "Newton", "Darwin", "Kepler",
        "Hubble", "Galileo", "Archimedes", "Euclid", "Pythagoras", "Copernicus", "Brahe", "Gauss",
        "Euler", "Fermat", "Turing", "Boole", "Shannon", "Fibonacci", "Pascal", "Leibniz",
        "Bernoulli", "Lagrange", "Fourier", "Laplace", "Poisson", "Taylor", "Maclaurin", "Hamilton",
        "Dirac", "Feynman", "Heisenberg", "Schrodinger", "Planck", "Pauli", "Born", "Bohr",
        "Rutherford", "Thomson", "Cavendish", "Faraday", "Maxwell", "Hertz", "Ampere", "Volta",
        "Ohm", "Coulomb", "Gauss", "Tesla", "Weber", "Gilbert", "Babbage", "VonNeumann",
        "Mandelbrot", "Kolmogorov", "Wiener", "Klein", "Lorentz", "Mach", "Carnot", "Joule",
        "Kelvin", "Rankine", "Reynolds", "Prandtl", "Bernoulli", "Coriolis", "Froude", "Mach",
        "Gibbs", "Helmholtz", "Rayleigh", "Euler", "Stokes", "Taylor", "Navier", "Stokes",
        "Lagrange", "Poisson", "Laplace", "Fourier", "Gauss", "Green", "Riemann", "Hilbert",
        "Lebesgue", "Banach", "Borel", "Cantor", "Peano", "Russell", "Whitehead", "Hilbert",
        "Zermelo", "Fraenkel", "Turing", "Church", "Gödel", "Kleene", "Rosser", "Post",
        "Robinson", "Minsky", "McCarthy", "Knuth", "Backus", "Naur", "Wirth", "Dijkstra",
        "Hoare", "Parnas", "Bachman", "Brooks", "DeMarco", "Yourdon", "Peters", "Nolan",
        "Weinberg", "Patterson", "Hennessy", "Torvalds", "Stallman", "BernersLee", "Kay",
        "Engelbart", "Liskov", "Abelson", "Sussman", "Steele", "Wall", "VanRossum", "Gosling",
        "Stroustrup", "Ritchie", "Thompson", "Kernighan", "Pike", "Kudryavtsev", "Golomb",
        "Karp", "Miller", "Papadimitriou", "Hopcroft", "Ullman", "Aho", "Lamport", "Codd",
        "Bachman", "Date", "Stonebraker", "Gray", "Hansen", "Booch", "Rumbaugh", "Jacobson",
        "Harel", "Pressman", "McConnell", "Brooks", "Gamma", "Helm", "Johnson", "Vlissides",
        "Buschmann", "Meunier", "Riehle", "Siberski", "Schaert", "Coplien", "Kerievsky",
        "Alexander", "Jones", "Cook", "Bauer", "Whitehead", "Cameron", "Fowler", "Beck",
        "Cunningham", "Jefferies", "Grenning", "Martin", "Cockburn", "Schwaber", "Sutherland",
        "Larman", "Vodde", "Adzic", "Saddington", "North", "Hanselman", "Norris", "Atwood",
        "Spolsky", "Hanselman", "Guthrie", "Hejlsberg", "Marlow", "Meyers", "Stevens",
        "Fitzpatrick", "Crockford", "Allen", "Siebel", "Heaton", "Schmidt", "Schwarz", "Beck",
        "Evans", "Thompson", "Cox", "Ousterhout", "Raymond", "Oram", "Spinellis", "Glass",
        "Brooks", "DeMarco", "McConnell", "Hunt", "Thomas", "Hickson", "Resig", "Souders",
        "Tate", "Fowler", "Gamma", "Beck", "Evans", "Freeman", "Johnson", "Jacobson",
        "Kruchten", "Larman", "Poppendieck", "Poppendieck", "Cohn", "Martin", "Cunningham",
        "Sutherland", "Schwaber", "Krebs", "Cohn", "Schwaber", "Beck", "Osterwalder",
        "Pigneur", "Groen", "Maas", "Seddon", "Gerrits", "Bosman", "Otto", "Seddon",
        "Groen", "VanAuken", "Fowler", "Beck", "Buschmann", "Stahl", "Voelter", "Newkirk",
        "Martin", "Savitch", "Liang", "Horstmann", "Cornell", "Bates", "Sierra", "Deitel",
        "Dietel", "Blaha", "Rumbaugh", "Booch", "Meyer", "Somerville", "Pressman", "Jacobson",
        "Gomaa", "Gamma", "Helm", "Johnson", "Vlissides", "Buschmann", "Meunier", "Riehle",
        "Siberski", "Schaert", "Coplien", "Kerievsky", "Martin", "Larman", "Fowler",
        "Cockburn", "Jeffries", "Adzic", "North", "Brooks", "Coplien", "Hunt", "Thomas",
        "McConnell", "DeMarco", "Booth", "Fowler", "Beck", "Evans", "Newkirk", "Sutherland",
        "Schwaber", "Fowler", "Adzic", "North", "Cockburn", "Jeffries", "Martin", "Fowler",
        "Beck", "Evans", "Newkirk", "Martin", "Savitch", "Liang", "Horstmann", "Cornell",
        "Bates", "Sierra", "Deitel", "Deitel", "Gomaa", "Booch", "Rumbaugh", "Jacobson",
        "Somerville", "Pressman", "Meyer", "Gamma", "Helm", "Johnson", "Vlissides", "Buschmann",
        "Meunier", "Riehle", "Siberski", "Schaert", "Coplien", "Kerievsky", "Martin", "Larman",
        "Fowler", "Cock"
    };

    public static final String getRandomPseudo() {
        return Mathf.random(Pseudos.DATAS);
    }
    
}
