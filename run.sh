rm -rf build/
mkdir -p build/classes

javac --release 17 -parameters -cp "lib/*" -d build/classes $(find . -name "*.java")

if [ $? -ne 0 ]; then
    echo "Erreur de compilation"
    exit 1
fi

jar cf framework.jar -C build/classes .
cp framework.jar /home/chiu/Documents/ITU/L3/S5/Mr_Naina/SPRINT/NOW/testFramework_3914/

cd /home/chiu/Documents/ITU/L3/S5/Mr_Naina/SPRINT/NOW/testFramework_3914/
sudo -v
./deploy.sh