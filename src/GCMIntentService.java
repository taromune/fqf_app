import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import afmaga.es.frikiquefriki.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;


public class GCMIntentService extends GCMBaseIntentService {
	private SharedPreferences prefs;
	@Override
	protected void onError(Context context, String errorId) {
		//Log.d("GCMTest", "REGISTRATION: Error -> " + errorId);
		
	}

	@Override
	protected void onMessage(Context context, Intent intent) {
		String msg = intent.getExtras().getString("msg");
	    //Log.d("GCMTest", "Mensaje: " + msg);
	    mostrarNotificacion(context, msg);
		
	}

	@Override
	protected void onRegistered(Context context, String regId) {		
	//	Log.d("GCMTest", "REGISTRATION: Registrado OK.");
		
        prefs = getSharedPreferences("FQFConfiguration",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();	    
	    editor.putString("regId", regId);
	    editor.commit();
	    	    
	   // registroServidor(regId);
		
	}

	@Override
	protected void onUnregistered(Context context, String arg1) {
	//	Log.d("GCMTest", "REGISTRATION: Desregistrado OK.");
		
	}
	
	private void mostrarNotificacion(Context context, String msg)
	{
	    //Obtenemos una referencia al servicio de notificaciones
	    String ns = Context.NOTIFICATION_SERVICE;
	    NotificationManager notManager = (NotificationManager) context.getSystemService(ns);
	 
	    //Configuramos la notificación
	    int icono = android.R.drawable.stat_sys_warning;
	    CharSequence textoEstado = "Alerta!";
	    long hora = System.currentTimeMillis();
	 
	    Notification notif = new Notification(icono, textoEstado, hora);
	 
	    //Configuramos el Intent
	    Context contexto = context.getApplicationContext();
	    CharSequence titulo = "Nuevo Mensaje";
	    CharSequence descripcion = msg;
	 
	    Intent notIntent = new Intent(contexto, GCMIntentService.class);
	 
	    PendingIntent contIntent = PendingIntent.getActivity(contexto, 0, notIntent, 0);
	 
	    notif.setLatestEventInfo(contexto, titulo, descripcion, contIntent);
	 
	    //AutoCancel: cuando se pulsa la notificaión ésta desaparece
	    notif.flags |= Notification.FLAG_AUTO_CANCEL;
	    
	    //Añadir sonido, vibración y luces
	    
	    prefs = getSharedPreferences("FQFConfiguration",Context.MODE_PRIVATE);
	        
	    boolean sound = prefs.getBoolean("sound", true);       
        boolean vibrate = prefs.getBoolean("vibrate", true);
        boolean led = prefs.getBoolean("led", true);
	    
        if(sound){
        	notif.defaults |= Notification.DEFAULT_SOUND;
	    }
	    if(vibrate){
	    	notif.defaults |= Notification.DEFAULT_VIBRATE;
	    }
	    
	    if(led){
	    	notif.defaults |= Notification.DEFAULT_LIGHTS;
		}
	    
	    
	    //Enviar notificación
	    notManager.notify(1, notif);
	}
	
	private void registroServidor(String usuario, String regId)
	{
		final String NAMESPACE = "http://www.frikiquefriki.es/";
		final String URL="http://10.0.2.2:1634/ServicioRegistroGCM.asmx";
		final String METHOD_NAME = "RegistroCliente";
		final String SOAP_ACTION = "http://sgoliver.net/RegistroCliente";

		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		
		request.addProperty("usuario", usuario); 
		request.addProperty("regGCM", regId); 

		SoapSerializationEnvelope envelope = 
				new SoapSerializationEnvelope(SoapEnvelope.VER11);
		
		envelope.dotNet = true; 

		envelope.setOutputSoapObject(request);

		HttpTransportSE transporte = new HttpTransportSE(URL);

		try 
		{
			transporte.call(SOAP_ACTION, envelope);

			SoapPrimitive resultado_xml =(SoapPrimitive)envelope.getResponse();
			String res = resultado_xml.toString();
			
			if(res.equals("1"))
				Log.d("GCMTest", "Registro WS: OK.");
		} 
		catch (Exception e) 
		{
			Log.d("GCMTest", "Registro WS: NOK. " + e.getCause() + " || " + e.getMessage());
		} 
	}
	
	public GCMIntentService() {
	    super("224338875065");
	}
}
