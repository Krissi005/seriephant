const Button = ({text, onClick, id, buttonType}) => {

    return (
        <button id={id} onClick={onClick}
                className={"btn "+ buttonType}>
            {text}
        </button>
    )
}

Button.defaultProps = {
    disabled: false
}

export default Button