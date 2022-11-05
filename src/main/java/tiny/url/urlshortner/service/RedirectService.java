package tiny.url.urlshortner.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import tiny.url.urlshortner.entity.Redirect;
import tiny.url.urlshortner.exception.BadRequestException;
import tiny.url.urlshortner.exception.NotFoundException;
import tiny.url.urlshortner.repository.RedirectRepository;
import tiny.url.urlshortner.request.RedirectCreationRequest;

@Service
public class RedirectService {

    private RedirectRepository redirectRepository;

    public RedirectService(RedirectRepository redirectRepository)
    {
        this.redirectRepository = redirectRepository;
    }

    public Optional<Redirect> createRedirect(RedirectCreationRequest redirectCreationRequest){

        if(redirectRepository.existsByAlias(redirectCreationRequest.getAlias()))
        {
            throw new BadRequestException("Alias already exists");
        }

        System.out.println(" Redirect Request " + redirectCreationRequest.toString());

        Redirect redirect = redirectRepository.save(new Redirect(redirectCreationRequest.getAlias(), redirectCreationRequest.getUrl()));

        System.out.println("Redirect" + redirect);

        return Optional.ofNullable(redirect);
    }

    public Redirect getRedirect(String alias){
 Redirect redirect = redirectRepository.findByAlias(alias)
                .orElseThrow(() -> new NotFoundException("Hey we don't have that alias! Try making it"));
   return redirect;
    }
}
