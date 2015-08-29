# main.py -- this is what gets run, bruh
from app import PifiApp

# executed when device starts up
def main():

    print("Starting up.") #debug

    #instantiate new RiskbandApp obj & start it
    app = PifiApp()

if __name__ == "__main__": main()