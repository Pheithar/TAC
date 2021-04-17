from pynput import mouse

counter = 0

def on_click(x, y, button, pressed):
    global counter
    if button == mouse.Button.left and pressed:
        counter += 1

    if button == mouse.Button.right and pressed:
        counter = 0
        print("\n")

    if button == mouse.Button.middle and pressed:
        return False

    print("Clicks:", counter, end='\r')



print("Contador de 'clicks' izquierdos")
print("")
print("Para resetear el contador pulsar 'click derecho'")
print("Para salir pulsar 'click central'")


# Collect events until released
with mouse.Listener(on_click=on_click) as listener:
    listener.join()
