package org.superbiz.moviefun.albumsapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

public class AlbumClient {

    private String albumURL;
    private RestOperations restOperations;

    public AlbumClient(String albumURL, RestOperations restOperations) {
        this.albumURL = albumURL;
        this.restOperations = restOperations;
    }


    public void addAlbum(AlbumInfo album) {
        restOperations.postForEntity(albumURL, album, AlbumInfo.class);
    }

    public Object getAlbums() {
        ParameterizedTypeReference<List<AlbumInfo>> albumListType = new ParameterizedTypeReference<List<AlbumInfo>>() {
        };
        return restOperations.exchange(albumURL, GET, null, albumListType).getBody();

    }

    public Object find(long id) {
        return restOperations.getForEntity(albumURL + "/" + id, AlbumInfo.class).getBody();
    }
}
