package Controlador;

import Procesos.Mensajes;
import Vista.FrmLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlLogin implements ActionListener {

    private FrmLogin flogin;
    private boolean autenticado = false;

    public ControlLogin(FrmLogin flogin) {
        this.flogin = flogin;
        flogin.btnIniciarSesion.addActionListener(this);
    }

    public boolean isAutenticado() {
        return autenticado;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == flogin.btnIniciarSesion) {
            ingresar();
        }
    }

private void ingresar() {

    String usuario = flogin.txtUser.getText().trim();
    String clave = String.valueOf(flogin.txtContraseña.getPassword()).trim();

    if (usuario.equals("admin") && clave.equals("12345")) {
        autenticado = true;
        flogin.dispose();
    } else {
        Mensajes.M1("Usuario o contraseña incorrectos");
        autenticado = false;
    }
}
}