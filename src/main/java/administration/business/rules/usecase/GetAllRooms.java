package administration.business.rules.usecase;

import administration.business.rules.usecase.output.HotelPresenter;

public interface GetAllRooms {
    void execute(HotelPresenter presenter);
}
