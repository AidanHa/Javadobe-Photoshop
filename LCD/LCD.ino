#include <LiquidCrystal.h>
#include "LCD.h"
#include "LCD.C"
// initialize the library by associating any needed LCD interface pin
// with the arduino pin number it is connected to
const int rs = 2, en = 3, d4 = 8, d5 = 9, d6 = 10, d7 = 11;
LiquidCrystal lcd(rs, en, d4, d5, d6, d7);
daq_holder_t temp;

daq_holder_t* daq= &temp;

void setup() {
  daq->pressure1 = 7.6767;
  daq->pressure2 = 14;  
  daq->pressure3 = 8;
  // set up the LCD's number of columns and rows:
  lcd.begin(20, 4);
  lcd_init(lcd);
}

void loop() {
  daq->pressure1 = 0;
  daq->pressure2 = 133;
  daq->pressure3 = 131;
  daq->ign_pri_current = 12;
  daq->ign_sec_current = 13;
  daq->rocket_mass = 89;
  lcd_update(daq, lcd);
  delay(1000);
  daq->pressure1 = 700.6767;
  daq->pressure2 = 14.6767;
  daq->pressure3 = 8;
  daq->ign_pri_current = 311;
  daq->ign_sec_current = 313;
  daq->rocket_mass = 88;
  lcd_update(daq, lcd);
  delay(1000);
}
