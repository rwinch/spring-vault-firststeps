package sample;

import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultClient;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponseSupport;

public class VaultApp {

	public static void main(String[] args) {

		VaultClient vaultClient = new VaultClient();
		VaultTemplate vaultTemplate = new VaultTemplate(vaultClient,
				new TokenAuthentication("85adc054-d3bd-bf68-28ad-0ae8a92828a7"));

		Secrets secrets = new Secrets();
		secrets.username = "hello";
		secrets.password = "world";

		vaultTemplate.write("secret/myapp", secrets);

		VaultResponseSupport<Secrets> response = vaultTemplate.read("secret/myapp", Secrets.class);
		System.out.println(response.getData().getUsername());

		vaultTemplate.delete("secret/myapp");
	}
}