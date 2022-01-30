import { Wrapper } from './styled';

const ForYouMain = () => {
  return (
    <Wrapper>
      <section className="category">
        <div className="tag-button HEALING">#HEALING</div>
        <div className="tag-button TASTE">#TASTE</div>
        <div className="tag-button ACTIVITY">#ACTIVITY</div>
        <div className="tag-button CHATTY">#CHATTY</div>
      </section>
      <section className="showall-button-box">
        <button className="showall-button">전체보기</button>
      </section>
    </Wrapper>
  );
};

export default ForYouMain;
