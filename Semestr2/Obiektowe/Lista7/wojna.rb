class Karta
  include Comparable

  @@wydane_karty = {} #zmienna klasowa wspolna dla wszystkich instancji (gdy jest @ to kazda instancja ma wlasna)

  FIGURY = [2, 3, 4, 5, 6, 7, 8, 9, 10, :J, :Q, :K, :A]
  KOLORY = [:kier, :karo, :pik, :trefl]
  SYMBOLE = {kier: "♡", karo: "♢", pik: "♠", trefl: "♣"}

  attr_reader :figura, :kolor #mamy dostep poza klasa

  def self.wydaj(figura, kolor)
    klucz = [figura, kolor]
    @@wydane_karty[klucz] ||= Karta.new(figura, kolor) #jesli jest w zbiorze to nie towrzy jej drugi raz
  end

  def self.talia
    FIGURY.product(KOLORY).map { |f, k| self.wydaj(f, k) } #dla kazdej pary figura-kolor tworzy karte za pomoca wydaj
  end

  def initialize(figura, kolor)
    @figura = figura
    @kolor = kolor
  end

  def to_s
    "#{@figura}#{SYMBOLE[@kolor]}"
  end

  def wartosc
    return @figura if @figura.is_a?(Integer)
    case @figura
    when :J then 11
    when :Q then 12
    when :K then 13
    when :A then 14
    end
  end

  def <=>(inna)
    self.wartosc <=> inna.wartosc
  end
end

class GraczCzlowiek
  attr_reader :nick, :karty, :punkty

  def initialize(nick)
    @nick = nick
    @karty = []
    @punkty = 0
  end

  def dodaj_karte(karta)
    @karty << karta #append
  end

  def ruch
    puts "#{@nick}, Twoje karty: #{@karty.map(&:to_s).join(', ')}"
    puts "Podaj numer karty do wyłożenia (0-#{@karty.size-1}):"
    idx = gets.to_i
    @karty.delete_at(idx)
  end

  def dodaj_punkt
    @punkty += 1
  end
end

class GraczKomputer
  attr_reader :nick, :karty, :punkty

  def initialize(nick)
    @nick = nick
    @karty = []
    @punkty = 0
  end

  def dodaj_karte(karte)
    @karty << karte
  end

  def ruch
    @karty.delete_at(rand(@karty.size))
  end

  def dodaj_punkt
    @punkty += 1
  end
end

class Gra
  def initialize(gracze)
    @gracze = gracze
    @wylozone = []
    @talia = Karta.talia.shuffle
  end

  def rozdaj
    until @talia.empty?
      @gracze.each { |g| g.dodaj_karte(@talia.pop) if !@talia.empty? } #for g in gracze ....
    end
  end

  def rozgrywka
    rozdaj
    26.times do |i|
      wylozone = @gracze.map { |g| g.ruch }
      puts "#{@gracze[0].nick} wyłożył: #{wylozone[0]}, #{@gracze[1].nick} wyłożył: #{wylozone[1]}"
      if wylozone[0] < wylozone[1]
        puts "#{@gracze[1].nick} wygrywa tę rundę"
        @gracze[1].dodaj_punkt
      elsif wylozone[0] > wylozone[1]
        puts "#{@gracze[0].nick} wygrywa tę rundę"
        @gracze[0].dodaj_punkt
      else
        puts "Remis"
      end
      puts "Wynik: #{@gracze[0].nick}: #{@gracze[0].punkty}, #{@gracze[1].nick}: #{@gracze[1].punkty}"
      puts "----"
    end
    puts "KONIEC GRY"
    puts "Ostateczny wynik:"
    puts "#{@gracze[0].nick}: #{@gracze[0].punkty}, #{@gracze[1].nick}: #{@gracze[1].punkty}"
    if @gracze[0].punkty > @gracze[1].punkty
      puts "Wygrał #{@gracze[0].nick}!"
    elsif @gracze[0].punkty < @gracze[1].punkty
      puts "Wygrał #{@gracze[1].nick}!"
    else
      puts "Remis!"
    end
  end
end

g1 = GraczCzlowiek.new("Człowiek")
# g1 = GraczKomputer.new("Komputer2")
g2 = GraczKomputer.new("Komputer")
gra = Gra.new([g1, g2])
gra.rozgrywka
